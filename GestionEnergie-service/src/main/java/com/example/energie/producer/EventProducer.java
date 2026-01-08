package com.example.energie.producer;

import static com.example.energie.config.RabbitMQConfig.SURCONSOMMATION_EXCHANGE;
import static com.example.energie.config.RabbitMQConfig.SURCONSOMMATION_ROUTING_KEY;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.energie.event.SurconsommationEvent;

@Service
public class EventProducer {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void envoyerAlerteSurconsommation(SurconsommationEvent event) {
        System.out.println("📤 Envoi d'alerte de surconsommation via RabbitMQ:");
        System.out.println("   Pompe: " + event.getPompeId());
        System.out.println("   Consommation: " + event.getConsommationObservee() + " kWh");
        System.out.println("   Seuil: " + event.getSeuil() + " kWh");
        System.out.println("   Message: " + event.getMessage());
        
        rabbitTemplate.convertAndSend(
            SURCONSOMMATION_EXCHANGE,
            SURCONSOMMATION_ROUTING_KEY,
            event
        );
        
        System.out.println("✅ Événement envoyé avec succès!");
    }
    
    // Helper method to create and send event
    public void envoyerAlerte(String pompeId, Double consommation, Double seuil) {
        String message = String.format(
            "ALERTE: Pompe %s a dépassé le seuil de consommation! " +
            "Consommation: %.2f kWh (Seuil: %.2f kWh)",
            pompeId, consommation, seuil
        );
        
        SurconsommationEvent event = new SurconsommationEvent(
            pompeId, consommation, seuil, message
        );
        
        envoyerAlerteSurconsommation(event);
    }
}