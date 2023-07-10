package com.nabhatek.catalogservice.web;

import com.nabhatek.catalogservice.domain.ProductNotFoundException;
import com.nabhatek.catalogservice.domain.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testInvalidProduct() throws Exception {
        given(productService.viewProductDetails(1111)).willThrow(ProductNotFoundException.class);
        mockMvc.perform(get("/products/1111")).andExpect(status().isNotFound());
    }
}
