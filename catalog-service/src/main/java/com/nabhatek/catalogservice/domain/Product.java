package com.nabhatek.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Product (
    @Id
    Long id,
    @NotBlank(message = "Product description cannot be empty.")
    String description,
    @NotNull(message = "Product price cannot be empty.")
    @Positive (message = "Product price must be greater than zero.")
    Double price,

    @CreatedDate
    Instant createdDate,

    @LastModifiedDate
    Instant lastModifiedDate,
    @Version
    int version
) {
    public static Product of(String description, Double price) {
        return new Product(null, description, price, null, null, 0);
    }
}
