package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.models.ConsommationElectrique;

public interface ConsommationRepository
        extends MongoRepository<ConsommationElectrique, String> {

    List<ConsommationElectrique> findByPompeId(String pompeId);
}
