package com.example.dto;

import java.time.LocalDate;

public record ConsommationRequest(
        String pompeId,
        Double energieUtilisee,
        Double duree,
        LocalDate dateMesure
) {}
