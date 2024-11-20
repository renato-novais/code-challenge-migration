package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");

        List<Product> products = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);

        List<Product> result = productController.getAllProducts();
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getTitle());
        assertEquals("Product 2", result.get(1).getTitle());
    }

    @Test
    void testGetAllProducts_EmptyList() {
        when(productService.getAllProducts()).thenReturn(List.of());

        List<Product> result = productController.getAllProducts();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");

        when(productService.getProductById(1L)).thenReturn(product);

        Product result = productController.getProductById(1L);
        assertNotNull(result);
        assertEquals("Product 1", result.getTitle());
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetProductById_NotFound() {
        when(productService.getProductById(99L)).thenReturn(null);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> productController.getProductById(99L)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Product not found", exception.getReason());
    }

    @Test
    void testGetProductById_InvalidId() {
        assertThrows(ResponseStatusException.class, () -> {
            productController.getProductById(null);
        });
    }
}