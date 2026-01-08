package com.example.eau.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.eau.event.SurconsommationEvent;

@Service
public class EventConsumer {
    
    @RabbitListener(queues = "surconsommation.queue")
    public void recevoirAlerteSurconsommation(SurconsommationEvent event) {
        System.out.println("\n🚨🚨🚨 ALERTE REÇUE DE ENERGY SERVICE 🚨🚨🚨");
        System.out.println("==============================================");
        System.out.println("Pompe ID: " + event.getPompeId());
        System.out.println("Consommation observée: " + event.getConsommationObservee() + " kWh");
        System.out.println("Seuil maximum: " + event.getSeuil() + " kWh");
        System.out.println("Message: " + event.getMessage());
        System.out.println("Timestamp: " + event.getTimestamp());
        System.out.println("==============================================");
        
        // Business logic - what to do when overconsumption is detected
        gererSurconsommation(event);
    }
    
    private void gererSurconsommation(SurconsommationEvent event) {
        System.out.println("🔄 Traitement de l'alerte de surconsommation...");
        
        // Example actions:
        // 1. Log to database
        // 2. Send notification to operator
        // 3. Stop the pump if needed
        // 4. Reduce water flow
        
        System.out.println("✅ Alerte traitée pour la pompe " + event.getPompeId());
        System.out.println("Action recommandée: Vérifier la pompe et réduire le débit d'eau si nécessaire.");
    }
}