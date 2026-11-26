package com.aryan.api_automation.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public Long userId;

    @NotNull
    public Long productId;

    @NotNull
    @Min(1)
    public Integer quantity;

    public String status;
}
