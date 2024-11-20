package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private final WebClient webClient;

    @Autowired
    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Product> getAllProducts() {
        Product[] products = webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToMono(Product[].class)
                .block();
        return Arrays.asList(products);
    }

    public Product getProductById(Long id) {
        return webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }
}
