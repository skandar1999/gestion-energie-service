package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.ConsommationRequest;
import com.example.dto.ConsommationResponse;
import com.example.energie.event.SurconsommationEvent;
import com.example.energie.producer.EventProducer;
import com.example.models.ConsommationElectrique;
import com.example.repository.ConsommationRepository;
import com.example.repository.PompeRepository;

@Service
public class ConsommationService {

    private final ConsommationRepository consommationRepository;
    private final PompeRepository pompeRepository;
    private final EventProducer eventProducer;
    
    private static final Double SEUIL_MAX_CONSOMMATION = 1000.0; // kWh threshold

    public ConsommationService(
            ConsommationRepository consommationRepository,
            PompeRepository pompeRepository,
            EventProducer eventProducer) {  // ADD EventProducer
        this.consommationRepository = consommationRepository;
        this.pompeRepository = pompeRepository;
        this.eventProducer = eventProducer;
    }

    public ConsommationResponse create(ConsommationRequest request) {
        System.out.println("🔍 Création consommation pour pompe: " + request.pompeId());
        System.out.println("   Énergie utilisée: " + request.energieUtilisee() + " kWh");
        System.out.println("   Durée: " + request.duree() + " heures");

        if (!pompeRepository.existsById(request.pompeId())) {
            throw new RuntimeException("Pompe not found");
        }

        ConsommationElectrique entity = new ConsommationElectrique(
                request.pompeId(),
                request.energieUtilisee(),
                request.duree(),
                request.dateMesure()
        );

        // Save to database
        ConsommationElectrique saved = consommationRepository.save(entity);
        System.out.println("✅ Consommation enregistrée avec ID: " + saved.getId());
        
        // Check for overconsumption and send alert if needed
        verifierSurconsommation(request.pompeId(), request.energieUtilisee());

        return mapToResponse(saved);
    }

    // NEW METHOD: Check for overconsumption
    private void verifierSurconsommation(String pompeId, Double energieUtilisee) {
        System.out.println("🔍 Vérification surconsommation pour pompe " + pompeId);
        System.out.println("   Consommation: " + energieUtilisee + " kWh");
        System.out.println("   Seuil maximum: " + SEUIL_MAX_CONSOMMATION + " kWh");
        
        if (energieUtilisee > SEUIL_MAX_CONSOMMATION) {
            System.out.println("🚨 ALERTE: Surconsommation détectée!");
            
            // Create alert message
            String message = String.format(
                "ALERTE: Pompe %s a dépassé le seuil de consommation! " +
                "Consommation: %.2f kWh (Seuil: %.2f kWh)",
                pompeId, energieUtilisee, SEUIL_MAX_CONSOMMATION
            );
            
            // Create and send event
            SurconsommationEvent event = new SurconsommationEvent(
                pompeId, 
                energieUtilisee, 
                SEUIL_MAX_CONSOMMATION, 
                message
            );
            
            eventProducer.envoyerAlerteSurconsommation(event);
        } else {
            System.out.println("✅ Consommation normale");
        }
    }

    // READ ALL
    public List<ConsommationResponse> findAll() {
        return consommationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // READ BY ID
    public ConsommationResponse findById(String id) {
        ConsommationElectrique c = consommationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consommation not found"));
        return mapToResponse(c);
    }

    // READ BY POMPE
    public List<ConsommationResponse> findByPompeId(String pompeId) {

        if (!pompeRepository.existsById(pompeId)) {
            throw new RuntimeException("Pompe not found");
        }

        return consommationRepository.findByPompeId(pompeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE avec vérification pompe
    public ConsommationResponse update(String id, ConsommationRequest request) {
        System.out.println("🔄 Mise à jour consommation ID: " + id);

        ConsommationElectrique existing = consommationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consommation not found"));

        if (!pompeRepository.existsById(request.pompeId())) {
            throw new RuntimeException("Pompe not found");
        }

        existing.setPompeId(request.pompeId());
        existing.setEnergieUtilisee(request.energieUtilisee());
        existing.setDuree(request.duree());
        existing.setDateMesure(request.dateMesure());

        ConsommationElectrique updated = consommationRepository.save(existing);
        System.out.println("✅ Consommation mise à jour");
        
        // Check for overconsumption on update too
        verifierSurconsommation(request.pompeId(), request.energieUtilisee());

        return mapToResponse(updated);
    }

    // DELETE avec vérification
    public void delete(String id) {
        System.out.println("🗑️  Suppression consommation ID: " + id);

        if (!consommationRepository.existsById(id)) {
            throw new RuntimeException("Consommation not found");
        }

        consommationRepository.deleteById(id);
        System.out.println("✅ Consommation supprimée");
    }

    private ConsommationResponse mapToResponse(ConsommationElectrique c) {
        return new ConsommationResponse(
                c.getId(),
                c.getPompeId(),
                c.getEnergieUtilisee(),
                c.getDuree(),
                c.getDateMesure()
        );
    }
    
    // NEW METHOD: Manual trigger for testing
    public String testerSurconsommation(String pompeId, Double consommation) {
        System.out.println("🧪 Test manuel de surconsommation");
        verifierSurconsommation(pompeId, consommation);
        return "Test effectué pour pompe " + pompeId + " avec consommation " + consommation + " kWh";
    }
}