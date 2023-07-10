package com.nabhatek.catalogservice.domain;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> viewProducts() {
        return productRepository.findAll();
    }

    public Product viewProductDetails(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product addProduct(Product product) {
        Optional<Product> byId = productRepository.findById(product.id());
        if (byId.isPresent()) {
            throw new ProductAlreadyExistsException(product.id());
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Product editProduct(Integer id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    var productToUpdate = new Product(
                            existingProduct.id(),
                            product.description(),
                            product.price()
                    );
                    return productRepository.save(productToUpdate);
                })
                .orElseGet(() -> addProduct(product));
    }

}
