package com.example.dummyjson.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = {"api.base-url=https://dummyjson.com"})
class WebClientConfigTest {

    @Autowired
    private WebClient webClient;

    @Test
    void testWebClient() {
        assertNotNull(webClient, "WebClient should be configured");
    }
}
