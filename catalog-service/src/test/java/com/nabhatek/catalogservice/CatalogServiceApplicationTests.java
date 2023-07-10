package com.nabhatek.catalogservice;

import com.nabhatek.catalogservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

	private WebTestClient webTestClient; // can use field level auto wiring but constructor version is preferred.

	@Autowired
	public CatalogServiceApplicationTests(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	@Test
	void createProduct() {
		var expected = new Product(4717, "Mixed Pickle", 5.99);
		webTestClient.post()
				.uri("/products")
				.bodyValue(expected)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(Product.class).value(actual -> {
					assertThat(actual).isNotNull();
					assertThat(actual.id()).isEqualTo(expected.id());
				});
	}

}
