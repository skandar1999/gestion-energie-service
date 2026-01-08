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

import com.example.models.Reservoir;
import com.example.service.ReservoirService;

@RestController
@RequestMapping("/api/reservoirs")
public class ReservoirController {

    private final ReservoirService service;

    public ReservoirController(ReservoirService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservoir create(@RequestBody Reservoir r) {
        return service.create(r);
    }

    @GetMapping
    public List<Reservoir> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Reservoir getById(@PathVariable String id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
