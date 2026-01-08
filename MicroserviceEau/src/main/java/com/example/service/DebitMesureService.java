package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.client.EnergieClient;
import com.example.models.DebitMesure;
import com.example.repository.DebitMesureRepository;

@Service
public class DebitMesureService {

    private final DebitMesureRepository repository;
    private final EnergieClient energieClient;

    public DebitMesureService(
            DebitMesureRepository repository,
            EnergieClient energieClient) {
        this.repository = repository;
        this.energieClient = energieClient;
    }

    // CREATE
    public DebitMesure create(DebitMesure debit) {

        if (!energieClient.pompeExisteEtActive(debit.getPompeId())) {
            throw new IllegalArgumentException(
                    "Pompe inexistante ou OFF");
        }

        return repository.save(debit);
    }

    // READ ALL
    public List<DebitMesure> findAll() {
        return repository.findAll();
    }

    // READ BY ID
    public DebitMesure findById(String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Débit non trouvé"));
    }

    // READ BY POMPE
    public List<DebitMesure> findByPompeId(String pompeId) {
        return repository.findByPompeId(pompeId);
    }

    // DELETE
    public void delete(String id) {
        DebitMesure d = findById(id);

        if (!energieClient.pompeExiste(d.getPompeId())) {
            throw new IllegalArgumentException(
                    "Impossible de supprimer : pompe inexistante");
        }

        repository.deleteById(id);
    }
}
