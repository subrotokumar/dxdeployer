package dev.subrotokumar.authentication.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HealthCheck {
    @GetMapping("/api/v1/health")
    public String healthCheck() {
        return "Server is running";
    }
}
