package com.aryan.api_automation.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String name;

    @NotNull
    @Positive
    public Double price;

    @NotNull
    @PositiveOrZero
    public Integer stock;
}
