package com.kadiraksoy.enoca_task.service;


import com.kadiraksoy.enoca_task.dto.response.OrderDto;
import com.kadiraksoy.enoca_task.entity.Cart;
import com.kadiraksoy.enoca_task.entity.Order;
import com.kadiraksoy.enoca_task.entity.OrderItem;
import com.kadiraksoy.enoca_task.entity.Product;
import com.kadiraksoy.enoca_task.exception.CartNotFoundException;
import com.kadiraksoy.enoca_task.exception.OrderNotFoundException;
import com.kadiraksoy.enoca_task.mapper.OrderConverter;
import com.kadiraksoy.enoca_task.repository.*;
import com.kadiraksoy.enoca_task.util.OrderCodeGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderConverter orderConverter;
    private final OrderCodeGenerator generator;

    public OrderService(
            OrderRepository orderRepository,
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            OrderConverter orderConverter,
            OrderCodeGenerator generator) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderConverter = orderConverter;
        this.generator = generator;
    }

    @Transactional
    public OrderDto placeOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart bulunamadı"));
        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrice());


        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Yeterli ürün yok: " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());


            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        String code = generator.generateOrderCode();
        order.setCode(code);
        orderRepository.save(order);


        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);


        return orderConverter.toOrderDTO(order);
    }

    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order bulunamadı"));

        return orderConverter.toOrderDTO(order);
    }

    public List<OrderDto> getAllOrdersForCustomer(Long customerId) {
       List<Order> orderList = orderRepository.findAllByCustomerId(customerId);
       return orderList.stream().map(order -> orderConverter.toOrderDTO(order)).collect(Collectors.toList());
    }

    public OrderDto getOrderByCode(String code) {
        Order order = orderRepository.findByCode(code)
                .orElseThrow(() -> new OrderNotFoundException("Order bulunamadı"));
        return orderConverter.toOrderDTO(order);
    }
}
