package com.kadiraksoy.enoca_task.repository;

import com.kadiraksoy.enoca_task.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
