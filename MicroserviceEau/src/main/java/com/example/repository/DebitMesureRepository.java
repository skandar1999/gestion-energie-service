package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.models.DebitMesure;

public interface DebitMesureRepository
        extends MongoRepository<DebitMesure, String> {

    List<DebitMesure> findByPompeId(String pompeId);
}
