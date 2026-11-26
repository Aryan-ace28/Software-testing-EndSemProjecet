package com.aryan.api_automation.payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public Long orderId;

    @NotNull
    @Positive
    public Double amount;

    public String status; // set by server
}
