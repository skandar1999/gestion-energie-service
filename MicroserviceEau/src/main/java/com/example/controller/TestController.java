package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.EnergieClient;

@RestController
public class TestController {
    
    @Autowired
    private EnergieClient energieClient;
    
    @GetMapping("/test")
    public String test() {
        return "✅ Water Service running on port 8082";
    }
    
    @GetMapping("/test-energy-simple/{pompeId}")
    public String testEnergySimple(@PathVariable("pompeId") String pompeId) {  // ADD ("pompeId")
        System.out.println("Testing with pompe: " + pompeId);
        
        try {
            boolean exists = energieClient.pompeExiste(pompeId);
            return "Simple test: pompeExiste returned " + exists;
        } catch (Exception e) {
            return "Error in pompeExiste: " + e.getClass().getName() + ": " + e.getMessage();
        }
    }

    @GetMapping("/test-energy-active/{pompeId}")
    public String testEnergyActive(@PathVariable("pompeId") String pompeId) {  // ADD ("pompeId")
        System.out.println("Testing active check for pompe: " + pompeId);
        
        try {
            boolean active = energieClient.pompeExisteEtActive(pompeId);
            return "Active test: pompeExisteEtActive returned " + active;
        } catch (Exception e) {
            return "Error in pompeExisteEtActive: " + e.getClass().getName() + ": " + e.getMessage();
        }
    }
}