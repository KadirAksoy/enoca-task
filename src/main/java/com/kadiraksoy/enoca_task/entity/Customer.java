package com.kadiraksoy.enoca_task.entity;

import com.kadiraksoy.enoca_task.entity.abstracts.BaseEntity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name="customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    @NotBlank
    @Size(min=2,max=40)
    @Column(name="name")
    private String name;

    @NotBlank
    @Size(min=8,max=45)
    @Column(name="email")
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    private List<Order> orders;
}
