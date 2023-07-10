package com.nabhatek.catalogservice.domain;

import java.util.Optional;

public interface ProductRepository {
    Iterable<Product> findAll();
    Optional<Product> findById(Integer id);
    Product save(Product product);
    void deleteById(Integer id);
}
