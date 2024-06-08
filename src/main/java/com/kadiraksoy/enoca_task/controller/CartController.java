package com.kadiraksoy.enoca_task.controller;

import com.kadiraksoy.enoca_task.dto.request.CartItemRequest;
import com.kadiraksoy.enoca_task.dto.response.CartDto;
import com.kadiraksoy.enoca_task.entity.Cart;
import com.kadiraksoy.enoca_task.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long customerId) {
        CartDto cart = cartService.getCart(customerId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<CartDto> addProductToCart(@RequestBody CartItemRequest cartItemRequest) {
        CartDto cart = cartService.addProductToCart(cartItemRequest);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/remove")
    public ResponseEntity<CartDto> removeProductFromCart(@RequestBody CartItemRequest cartItemRequest) {
        CartDto cart = cartService.removeProductFromCart(cartItemRequest);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/empty/{cartId}")
    public ResponseEntity<CartDto> emptyCart(@PathVariable Long cartId) {
        CartDto cart = cartService.emptyCart(cartId);
        return ResponseEntity.ok(cart);
    }
}
