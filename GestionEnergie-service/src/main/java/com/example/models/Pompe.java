package com.example.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pompes")
public class Pompe {

    @Id
    private String id;

    private String reference;
    private Double puissance;
    private String statut; // ON / OFF
    private LocalDate dateMiseEnService;
}
