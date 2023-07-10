package com.nabhatek.catalogservice.demo;

import com.nabhatek.catalogservice.domain.Product;
import com.nabhatek.catalogservice.domain.ProductRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class ProductDataLoader {
    private final ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadProductTestData() {
        var product1 = new Product(4713, "Ginger Mango Chutney", 2.99);
        var product2 = new Product(4717, "Mixed Pickle", 5.99);
        productRepository.save(product1);
        productRepository.save(product2);
    }
}
