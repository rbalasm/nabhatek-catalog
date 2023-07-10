package com.nabhatek.catalogservice.domain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id) {
        super("Product not found: " + id);
    }
}
