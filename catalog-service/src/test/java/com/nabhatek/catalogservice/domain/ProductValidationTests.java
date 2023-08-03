package com.nabhatek.catalogservice.domain;

import com.nabhatek.catalogservice.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validFields() {
        Instant now = Instant.now();
        var product = new Product(4717L, "Mixed Pickle", 5.99, now, now,0);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations).isEmpty();
    }

    @Test
    void inValidPrice() {
        Instant now = Instant.now();
        var product = new Product(4717L, "Mixed Pickle", 0.0, now, now, 0);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product price must be greater than zero.");
    }
}
