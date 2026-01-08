package com.example.client;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EnergieClient {

    private final RestTemplate restTemplate;

    public EnergieClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean pompeExiste(String pompeId) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(
                "http://localhost:8081/api/pompes/" + pompeId + "/exists",
                Map.class
            );
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Boolean.TRUE.equals(response.getBody().get("exists"));
            }
            return false;
        } catch (Exception e) {
            System.out.println("❌ Erreur vérification existence pompe: " + e.getMessage());
            return false;
        }
    }

    public boolean pompeExisteEtActive(String pompeId) {
        System.out.println("=== DEBUG EnergieClient.pompeExisteEtActive() ===");
        System.out.println("Received pompeId: '" + pompeId + "'");
        System.out.println("Length: " + pompeId.length());
        
        String url = "http://localhost:8081/api/pompes/" + pompeId + "/disponibilite";
        System.out.println("URL being called: " + url);
        
        try {
            System.out.println("Making REST call to Energy Service...");
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> body = response.getBody();
                System.out.println("Response keys: " + body.keySet());
                
                Object disponibleObj = body.get("disponible");
                System.out.println("'disponible' value: " + disponibleObj);
                
                boolean disponible = Boolean.TRUE.equals(disponibleObj);
                System.out.println("Final result: " + disponible);
                return disponible;
            }
            
            System.out.println("No valid response received");
            return false;
            
        } catch (Exception e) {
            System.out.println("❌ EXCEPTION in pompeExisteEtActive:");
            System.out.println("   Type: " + e.getClass().getName());
            System.out.println("   Message: " + e.getMessage());
            System.out.println("   Full URL that failed: " + url);
            e.printStackTrace();
            return false;
        }
    }
    
    public Map<String, Object> getPompeDetails(String pompeId) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(
                "http://localhost:8081/api/pompes/" + pompeId,
                Map.class
            );
            return response.getBody();
        } catch (Exception e) {
            System.out.println("❌ Erreur récupération détails pompe: " + e.getMessage());
            return null;
        }
    }
}