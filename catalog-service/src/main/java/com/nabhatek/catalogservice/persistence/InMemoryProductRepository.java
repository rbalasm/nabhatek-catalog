package com.nabhatek.catalogservice.persistence;

import com.nabhatek.catalogservice.domain.Product;
import com.nabhatek.catalogservice.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private static final Map<Integer, Product> products =  new ConcurrentHashMap<>();

    @Override
    public Iterable<Product> findAll() {
        return products.values();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return products.get(id) == null ? Optional.empty() : Optional.of(products.get(id));
    }

    @Override
    public Product save(Product product) {
        products.put(product.id(), product);
        return product;
    }

    @Override
    public void deleteById(Integer id) {
        products.remove(id);
    }
}
