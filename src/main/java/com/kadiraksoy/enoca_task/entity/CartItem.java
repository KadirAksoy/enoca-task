package com.kadiraksoy.enoca_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kadiraksoy.enoca_task.entity.abstracts.BaseEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name="cart_items")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CartItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(1)
    private int quantity;

    @ManyToOne
    @JoinColumn(name="cart_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Cart cart;
    private Double price;
}
