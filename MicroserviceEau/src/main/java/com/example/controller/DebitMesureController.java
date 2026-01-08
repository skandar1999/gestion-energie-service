package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.DebitMesure;
import com.example.service.DebitMesureService;

@RestController
@RequestMapping("/api/debits")
public class DebitMesureController {

    private final DebitMesureService service;

    public DebitMesureController(DebitMesureService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DebitMesure create(@RequestBody DebitMesure debit) {
        return service.create(debit);
    }

    // GET ALL
    @GetMapping
    public List<DebitMesure> getAll() {
        return service.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public DebitMesure getById(@PathVariable String id) {
        return service.findById(id);
    }

    // GET BY POMPE
    @GetMapping("/pompe/{pompeId}")
    public List<DebitMesure> getByPompe(
            @PathVariable String pompeId) {
        return service.findByPompeId(pompeId);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
