package com.kadiraksoy.enoca_task.entity;

import com.kadiraksoy.enoca_task.entity.abstracts.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name="carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cart extends BaseEntity {

    @Min(0)
    @Column(name="total_price")
    private Double totalPrice;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @OneToMany(mappedBy="cart")
    private Set<CartItem> items;
}
