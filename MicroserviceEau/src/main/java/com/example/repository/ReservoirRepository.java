package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.models.Reservoir;

public interface ReservoirRepository
        extends MongoRepository<Reservoir, String> {
}
