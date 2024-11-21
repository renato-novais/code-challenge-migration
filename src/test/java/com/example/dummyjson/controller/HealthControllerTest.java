package com.example.dummyjson.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HealthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testHealthEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/health", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status HTTP deve ser 200 OK");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");
        assertTrue(response.getBody().contains("\"status\":\"UP\""), "O status deve ser 'UP'");
        assertTrue(response.getBody().contains("\"description\":\"Microservice is running.\""),
                "A descrição deve indicar que o microsserviço está funcionando");
    }
}
