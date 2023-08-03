package com.nabhatek.catalogservice.web;

import com.nabhatek.catalogservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProductJsonTests {
    @Autowired
    private JacksonTester<Product> json;

    @Test
    void testSerialize() throws Exception {
        Instant now = Instant.now();
        var product = new Product(4717L, "Mixed Pickle", 5.99, now, now,0);
        var jsonContent = json.write(product);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(product.id().intValue());
        assertThat(jsonContent).extractingJsonPathStringValue("@.description").isEqualTo(product.description());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(product.price());
        assertThat(jsonContent).extractingJsonPathStringValue("@.createdDate").isEqualTo(product.createdDate().toString());
        assertThat(jsonContent).extractingJsonPathStringValue("@.lastModifiedDate").isEqualTo(product.lastModifiedDate().toString());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.version").isEqualTo(product.version());
    }

    @Test
    void testDeserialize() throws Exception {
        var instant = Instant.parse("2023-08-02T18:00:00.135029Z");
        var product = new Product(4717L, "Mixed Pickle", 5.99, instant, instant, 1);
        var productJson = """
                {
                    "id":4717,
                    "description":"Mixed Pickle",
                    "price":5.99,
                    "createdDate": "2023-08-02T18:00:00.135029Z",
                    "lastModifiedDate": "2023-08-02T18:00:00.135029Z",
                    "version": 1
                }                             
                """;

        assertThat(json.parse(productJson)).usingRecursiveComparison().isEqualTo(product);
    }
}
