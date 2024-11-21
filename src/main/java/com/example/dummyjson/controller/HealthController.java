package com.example.dummyjson.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Health", description = "Path that returns the health of the microservice")
@RestController
@RequestMapping("/health")
public class HealthController {

    @Operation(summary = "Check health", description = "Get microservice health")
    @GetMapping
    public ResponseEntity<Map<String, Object>> checkHealth() {
        Map<String, Object> healthStatus = new HashMap<>();
        healthStatus.put("status", "UP");
        healthStatus.put("timestamp", System.currentTimeMillis());
        healthStatus.put("description", "Microservice is running.");

        return ResponseEntity.ok(healthStatus);
    }
}
