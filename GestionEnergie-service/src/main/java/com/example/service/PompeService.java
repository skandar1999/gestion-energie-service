package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.PompeResponse;
import com.example.models.Pompe;
import com.example.repository.PompeRepository;

@Service
public class PompeService {

    private final PompeRepository repository;

    public PompeService(PompeRepository repository) {
        this.repository = repository;
    }

    public List<PompeResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(PompeResponse::fromEntity)
                .toList();
    }

    public PompeResponse findById(String id) {
        Pompe pompe = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pompe not found"));
        return PompeResponse.fromEntity(pompe);
    }

    public PompeResponse create(Pompe pompe) {
        return PompeResponse.fromEntity(repository.save(pompe));
    }

    public PompeResponse update(String id, Pompe pompe) {
        Pompe existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pompe not found"));

        existing.setReference(pompe.getReference());
        existing.setPuissance(pompe.getPuissance());
        existing.setStatut(pompe.getStatut());
        existing.setDateMiseEnService(pompe.getDateMiseEnService());

        return PompeResponse.fromEntity(repository.save(existing));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
