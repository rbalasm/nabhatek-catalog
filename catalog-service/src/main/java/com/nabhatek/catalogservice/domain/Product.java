package com.nabhatek.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record Product (
    @NotNull(message = "Product ID cannot be empty.")
    Integer id,
    @NotBlank(message = "Product description cannot be empty.")
    String description,
    @NotNull(message = "Product price cannot be empty.")
    @Positive (message = "Product price must be greater than zero.")
    Double price
) {}
