package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PompeNotFoundException
        extends RuntimeException {

    public PompeNotFoundException(String pompeId) {
        super("Pompe not found with id: " + pompeId);
    }
}
