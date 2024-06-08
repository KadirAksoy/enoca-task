package com.kadiraksoy.enoca_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kadiraksoy.enoca_task.entity.abstracts.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    @Column(name="total_price")
    private Double totalPrice;

    @Column(name="code",unique=true)
    private String code;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer customer;

}