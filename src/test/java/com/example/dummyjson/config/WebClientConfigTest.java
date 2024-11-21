package com.example.dummyjson.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WebClientConfigTest {

    @Autowired
    private WebClient webClient;

    @Test
    void testWebClientConfiguration() {
        assertNotNull(webClient, "The WebClient should be configured and not null");
    }
}
