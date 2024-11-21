package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .responseTimeout(java.time.Duration.ofMinutes(5))
                .build();
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = webTestClient.get()
                .uri("/api/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(products, "A lista de produtos não deve ser nula");
        assertFalse(products.isEmpty(), "A lista de produtos não deve estar vazia");
    }

    @Test
    void testGetProductById_ExistingId() {
        Product product = webTestClient.get()
                .uri("/api/products/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(product, "O produto não deve ser nulo");
        assertEquals(1L, product.getId(), "O ID do produto deve corresponder ao ID solicitado");
    }

}