package com.kadiraksoy.enoca_task.controller;

import com.kadiraksoy.enoca_task.dto.response.OrderDto;
import com.kadiraksoy.enoca_task.entity.Order;
import com.kadiraksoy.enoca_task.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder/{cartId}")
    public ResponseEntity<OrderDto> placeOrder(@PathVariable Long cartId) {
        OrderDto order = orderService.placeOrder(cartId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        OrderDto order = orderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        List<OrderDto> orders = orderService.getAllOrdersForCustomer(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<OrderDto> getOrderByCode(@PathVariable String code) {
        OrderDto order = orderService.getOrderByCode(code);
        return ResponseEntity.ok(order);
    }
}
