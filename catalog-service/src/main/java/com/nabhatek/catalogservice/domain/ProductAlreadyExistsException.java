package com.nabhatek.catalogservice.domain;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(Long Id) {
        super("Product already exists: " + Id);
    }
}
