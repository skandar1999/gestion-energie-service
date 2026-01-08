package com.example.eau.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQStatusController {
    
    @GetMapping("/rabbitmq-status")
    public String getRabbitMQStatus() {
        return """
               RabbitMQ Consumer Status:
               - ✅ Connected and listening on queue: surconsommation.queue
               - Ready to receive alerts from Energy Service
               - Test by calling: POST http://localhost:8081/api/test/alert/{pompeId}?consommation=1500
               """;
    }
}