package com.example.dto;

import java.time.LocalDate;

public record ConsommationResponse(
        String id,
        String pompeId,
        Double energieUtilisee,
        Double duree,
        LocalDate dateMesure
) {}
