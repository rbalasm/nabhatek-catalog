package com.nabhatek.catalogservice.domain;

import com.nabhatek.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class ProductRepositoryJdbcTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findProductById() {
        var product = Product.of("Mixed Pickle", 5.99);
        productRepository.deleteAll();
        jdbcAggregateTemplate.insert(product);
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> products = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        assertThat(products).hasSize(1);
    }
}
