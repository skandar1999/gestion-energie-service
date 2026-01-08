package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.repository")
@ComponentScan(basePackages = {
    "com.example",
    "com.example.controller",
    "com.example.service",
    "com.example.client",
    "com.example.repository"
})  // Try this explicit scanning
public class MicroserviceEauApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceEauApplication.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}