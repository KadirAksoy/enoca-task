package com.kadiraksoy.enoca_task.repository;

import com.kadiraksoy.enoca_task.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByCustomerId(Long customerId);
    Optional<Order> findByCode(String code);
}
