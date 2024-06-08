package com.kadiraksoy.enoca_task.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {

    private Long cartId;
    private Long productId;
    private int quantity;
}
