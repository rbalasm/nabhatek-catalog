package com.nabhatek.catalogservice.web;

import com.nabhatek.catalogservice.domain.Product;
import com.nabhatek.catalogservice.domain.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> get() {
        return productService.viewProducts();
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Integer id) {
        return productService.viewProductDetails(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product post(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @PutMapping("{id}")
    public Product put(@PathVariable Integer id, @Valid @RequestBody Product product) {
        return productService.editProduct(id, product);
    }
}
