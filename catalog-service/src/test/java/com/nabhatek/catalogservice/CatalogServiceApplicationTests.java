package com.nabhatek.catalogservice;

import com.nabhatek.catalogservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
//@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration")
class CatalogServiceApplicationTests {

	private WebTestClient webTestClient; // can use field level auto wiring but constructor version is preferred.

	@Autowired
	public CatalogServiceApplicationTests(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	@Test
	void createProduct() {
		Instant now = Instant.now();
//		var expected = new Product(4717L, "Mixed Pickle", 5.99, now, now,1);
		var productToCreate = Product.of("Mixed1 Pickle", 5.99);
		Product product = webTestClient.post()
				.uri("/products")
				.bodyValue(productToCreate)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(Product.class).value(actual -> {
					assertThat(actual).isNotNull();
//					assertThat(actual.id()).isEqualTo(expected.id());
				}).returnResult().getResponseBody();


		System.out.println("Returned Product ID: " + product.id());
	}

}
