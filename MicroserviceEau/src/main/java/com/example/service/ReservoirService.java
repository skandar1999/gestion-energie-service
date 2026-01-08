package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.Reservoir;
import com.example.repository.ReservoirRepository;

@Service
public class ReservoirService {

    private final ReservoirRepository repository;

    public ReservoirService(ReservoirRepository repository) {
        this.repository = repository;
    }

    public Reservoir create(Reservoir r) {
        return repository.save(r);
    }

    public List<Reservoir> findAll() {
        return repository.findAll();
    }

    public Reservoir findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservoir not found"));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
