package com.example.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "debits_mesures")
public class DebitMesure {

    @Id
    private String id;

    private String pompeId;
    private Double debit;
    private LocalDate dateMesure;
    private String unite; 
}
