package com.kadiraksoy.enoca_task.mapper;

import com.kadiraksoy.enoca_task.dto.response.OrderDto;
import com.kadiraksoy.enoca_task.dto.response.OrderItemDto;
import com.kadiraksoy.enoca_task.entity.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderConverter {
    public OrderDto toOrderDTO(Order order) {
        return new OrderDto(
                order.getId(),
                order.getTotalPrice(),
                order.getCode(),
                order.getCustomer().getId(),
                order.getItems().stream().map(orderItem -> new OrderItemDto(
                        orderItem.getId(),
                        orderItem.getProduct().getId(),
                        orderItem.getProduct().getName(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()
                )).collect(Collectors.toList())
        );
    }
}
