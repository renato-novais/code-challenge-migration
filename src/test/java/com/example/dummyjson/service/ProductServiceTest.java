package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products, "A lista de produtos não deve ser nula");
        assertFalse(products.isEmpty(), "A lista de produtos não deve estar vazia");
    }

    @Test
    void testGetProductById_ExistingId() {
        Product product = productService.getProductById(1L);
        assertNotNull(product, "O produto não deve ser nulo");
        assertEquals(1L, product.getId(), "O ID do produto deve corresponder ao ID solicitado");
    }

}
