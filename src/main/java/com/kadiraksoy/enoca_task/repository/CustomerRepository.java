package com.kadiraksoy.enoca_task.repository;

import com.kadiraksoy.enoca_task.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
