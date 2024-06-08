package com.kadiraksoy.enoca_task.repository;

import com.kadiraksoy.enoca_task.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
