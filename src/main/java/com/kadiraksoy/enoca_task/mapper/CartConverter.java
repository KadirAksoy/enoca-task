package com.kadiraksoy.enoca_task.mapper;


import com.kadiraksoy.enoca_task.dto.response.CartDto;
import com.kadiraksoy.enoca_task.dto.response.CartItemDto;
import com.kadiraksoy.enoca_task.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartConverter {
    public CartDto toCartDTO(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getTotalPrice(),
                cart.getCustomer().getId(),
                cart.getItems().stream().map(cartItem -> new CartItemDto(
                        cartItem.getId(),
                        cartItem.getProduct().getId(),
                        cartItem.getProduct().getName(),
                        cartItem.getQuantity(),
                        cartItem.getPrice()
                )).collect(Collectors.toSet())
        );
    }
}
