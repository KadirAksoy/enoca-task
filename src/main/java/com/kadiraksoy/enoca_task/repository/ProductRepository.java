package com.kadiraksoy.enoca_task.repository;

import com.kadiraksoy.enoca_task.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
