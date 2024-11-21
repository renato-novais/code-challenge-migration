package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final WebClient webClient;

    @Autowired
    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Product> getAllProducts() {
        Map<String, Object> response = webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return ((List<Map<String, Object>>) response.get("products")).stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public Product getProductById(Long id) {
        Map<String, Object> response = webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return mapToProduct(response);
    }

    private Product mapToProduct(Map<String, Object> productMap) {
        Product product = new Product();
        product.setId(((Number) productMap.get("id")).longValue());
        product.setTitle((String) productMap.get("title"));
        product.setDescription((String) productMap.get("description"));
        product.setPrice(((Number) productMap.get("price")).doubleValue());
        return product;
    }
}
