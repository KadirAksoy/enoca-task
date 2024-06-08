package com.kadiraksoy.enoca_task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private Double totalPrice;
    private Long customerId;
    private Set<CartItemDto> items;
}
