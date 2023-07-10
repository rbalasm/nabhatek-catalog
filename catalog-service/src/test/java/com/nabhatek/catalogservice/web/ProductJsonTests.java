package com.nabhatek.catalogservice.web;

import com.nabhatek.catalogservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProductJsonTests {
    @Autowired
    private JacksonTester<Product> json;

    @Test
    void testSerialize() throws Exception {
        var product = new Product(4717, "Mixed Pickle", 5.99);
        var jsonContent = json.write(product);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(product.id());
        assertThat(jsonContent).extractingJsonPathStringValue("@.description").isEqualTo(product.description());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(product.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var product = new Product(4717, "Mixed Pickle", 5.99);
        var productJson = """
                {
                    "id":4717,
                    "description":"Mixed Pickle",
                    "price":5.99
                }                             
                """;

        assertThat(json.parse(productJson)).usingRecursiveComparison().isEqualTo(product);
    }
}
