package com.nabhatek.catalogservice.domain;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(Integer Id) {
        super("Product already exists: " + Id);
    }
}
