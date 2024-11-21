package com.example.dummyjson.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Produto Teste");
        product.setDescription("Descrição do produto");
        product.setPrice(99.99);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertTrue(violations.isEmpty(), "O produto válido não deve gerar violações");
    }

    @Test
    void testInvalidProduct_NullFields() {
        Product product = new Product();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty(), "O produto inválido deve gerar violações");
        assertTrue(
                violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("id")),
                "O campo 'id' deve gerar uma violação"
        );
        assertTrue(
                violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")),
                "O campo 'title' deve gerar uma violação"
        );
        assertTrue(
                violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description")),
                "O campo 'description' deve gerar uma violação"
        );
        assertTrue(
                violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("price")),
                "O campo 'price' deve gerar uma violação"
        );
    }

    @Test
    void testInvalidProduct_InvalidId() {
        Product product = new Product();
        product.setId(1000L); // Fora do limite definido em @Max(999)
        product.setTitle("Produto Teste");
        product.setDescription("Descrição do produto");
        product.setPrice(99.99);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty(), "O produto inválido deve gerar violações");
        assertTrue(
                violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("id")),
                "O campo 'id' deve gerar uma violação de limite"
        );
    }
}
