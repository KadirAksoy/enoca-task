package com.kadiraksoy.enoca_task.entity;

import com.kadiraksoy.enoca_task.entity.abstracts.BaseEntity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

    @Column(name="price")
    @Min(0)
    private Double price;

    @Column(name="name")
    @NotBlank
    private String name;

    @Column(name="stock")
    @Min(0)
    private Integer stock;
}
