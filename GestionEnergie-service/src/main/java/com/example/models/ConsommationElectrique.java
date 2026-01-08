package com.example.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection = "consommations_electriques")
public class ConsommationElectrique {

    @Id
    private String id;

    private String pompeId;
    private Double energieUtilisee; 
    private Double duree;           
    private LocalDate dateMesure;

    // constructors
    public ConsommationElectrique() {}

    public ConsommationElectrique(
            String pompeId,
            Double energieUtilisee,
            Double duree,
            LocalDate dateMesure) {
        this.pompeId = pompeId;
        this.energieUtilisee = energieUtilisee;
        this.duree = duree;
        this.dateMesure = dateMesure;
    }

    // getters & setters
}
