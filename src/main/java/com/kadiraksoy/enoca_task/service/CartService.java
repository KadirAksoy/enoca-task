package com.kadiraksoy.enoca_task.service;


import com.kadiraksoy.enoca_task.dto.request.CartItemRequest;
import com.kadiraksoy.enoca_task.dto.response.CartDto;
import com.kadiraksoy.enoca_task.entity.Cart;
import com.kadiraksoy.enoca_task.entity.CartItem;
import com.kadiraksoy.enoca_task.entity.Product;
import com.kadiraksoy.enoca_task.exception.CartNotFoundException;
import com.kadiraksoy.enoca_task.exception.ProductNotFoundException;
import com.kadiraksoy.enoca_task.mapper.CartConverter;
import com.kadiraksoy.enoca_task.repository.CartItemRepository;
import com.kadiraksoy.enoca_task.repository.CartRepository;
import com.kadiraksoy.enoca_task.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartConverter cartConverter;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       CartConverter cartConverter) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartConverter = cartConverter;
    }
    public CartDto getCart(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        return cartConverter.toCartDTO(cart);
    }

    @Transactional
    public CartDto addProductToCart(CartItemRequest cartItemRequest) {
        Cart cart = cartRepository.findById(cartItemRequest.getCartId())
                .orElseThrow(() -> new CartNotFoundException("Kart bulunamadı"));
        Product product = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(cartItemRequest.getProductId()));

        if (product.getStock() < cartItemRequest.getQuantity()) {
            throw new RuntimeException("Ürün için yeterli stok yok");
        }

        CartItem existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(cartItemRequest.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            existingCartItem.setPrice(existingCartItem.getPrice() + product.getPrice() * cartItemRequest.getQuantity());
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice() * cartItemRequest.getQuantity());
            cart.getItems().add(cartItem);
            cartItemRepository.save(cartItem);
        }

        updateCartTotalPrice(cart);
        cartRepository.save(cart);
        log.info("Urun eklendi: " + cartItemRequest.getProductId());
        return cartConverter.toCartDTO(cart);
    }
    @Transactional
    public CartDto removeProductFromCart(CartItemRequest cartItemRequest) {
        Cart cart = cartRepository.findById(cartItemRequest.getCartId())
                .orElseThrow(() -> new CartNotFoundException("Kart bulunamadı"));
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(cartItemRequest.getProductId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(cartItemRequest.getProductId()));

        if (cartItem.getQuantity() <= cartItemRequest.getQuantity()) {
            cart.getItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - cartItemRequest.getQuantity());
            cartItem.setPrice(cartItem.getPrice() - cartItem.getProduct().getPrice() * cartItemRequest.getQuantity());
            cartItemRepository.save(cartItem);
        }

        updateCartTotalPrice(cart);
        cartRepository.save(cart);
        log.info("Urun silindi: " + cartItemRequest.getProductId());
        return cartConverter.toCartDTO(cart);
    }


    @Transactional
    public CartDto emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart bulunamadı."));
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
        return cartConverter.toCartDTO(cart);
    }

    private void updateCartTotalPrice(Cart cart) {
        double totalPrice = cart.getItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
    }

}
