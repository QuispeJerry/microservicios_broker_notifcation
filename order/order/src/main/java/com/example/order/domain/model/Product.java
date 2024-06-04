package com.example.order.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

