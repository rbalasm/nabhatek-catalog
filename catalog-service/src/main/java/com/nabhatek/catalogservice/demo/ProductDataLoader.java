package com.nabhatek.catalogservice.demo;

import com.nabhatek.catalogservice.domain.Product;
import com.nabhatek.catalogservice.domain.ProductRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@Profile("testdata")
public class ProductDataLoader {
    private final ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadProductTestData() {
        productRepository.deleteAll();
        Instant now = Instant.now();
        var product1 = Product.of("Ginger Mango Chutney", 2.99);
        var product2 = Product.of("Mixed Pickle", 5.99);
        productRepository.saveAll(List.of(product1, product2));
    }
}
