package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "reservoirs")
public class Reservoir {

    @Id
    private String id;
    private String nom;
    private Double capaciteTotale;
    private Double volumeActuel;
    private String localisation;
}
