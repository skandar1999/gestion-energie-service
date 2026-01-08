package com.example.energie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.energie.producer.EventProducer;

@RestController
@RequestMapping("/api/test")
public class TestEventController {
    
    @Autowired
    private EventProducer eventProducer;
    
    @PostMapping("/alert/{pompeId}")
    public String testAlerte(@PathVariable String pompeId, 
                           @RequestParam Double consommation) {
        
        Double seuil = 1000.0; // Example threshold
        
        if (consommation > seuil) {
            eventProducer.envoyerAlerte(pompeId, consommation, seuil);
            return String.format(
                "✅ Alerte envoyée pour pompe %s: %.2f kWh (seuil: %.2f kWh)",
                pompeId, consommation, seuil
            );
        } else {
            return String.format(
                "✅ Consommation normale pour pompe %s: %.2f kWh (seuil: %.2f kWh)",
                pompeId, consommation, seuil
            );
        }
    }
    
    @GetMapping("/test-alert")
    public String testSimpleAlerte() {
        // Send a test alert
        eventProducer.envoyerAlerte("TEST-POMPE-001", 1500.75, 1000.0);
        return "Test alerte envoyée!";
    }
}