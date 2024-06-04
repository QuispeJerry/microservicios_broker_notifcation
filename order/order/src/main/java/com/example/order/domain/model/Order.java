package com.example.order.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "\"order\"")  // Para H2 y PostgreSQL
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private Date orderDate;

    // Constructors, Getters and Setters
    public Order() {}

    public Order(Long productId, int quantity, Date orderDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

}