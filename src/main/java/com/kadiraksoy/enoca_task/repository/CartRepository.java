package com.kadiraksoy.enoca_task.repository;

import com.kadiraksoy.enoca_task.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCustomerId(Long customerId);
}
