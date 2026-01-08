package com.example.dto;

import java.time.LocalDate;

import com.example.models.Pompe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PompeResponse {

    private String id;
    private String reference;
    private Double puissance;
    private String statut;
    private LocalDate dateMiseEnService;

    public static PompeResponse fromEntity(Pompe p) {
        return PompeResponse.builder()
                .id(p.getId())
                .reference(p.getReference())
                .puissance(p.getPuissance())
                .statut(p.getStatut())
                .dateMiseEnService(p.getDateMiseEnService())
                .build();
    }
}
