package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Pompe;

@Repository
public interface PompeRepository extends MongoRepository<Pompe, String> {
}
