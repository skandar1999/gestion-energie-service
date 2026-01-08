package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ConsommationRequest;
import com.example.dto.ConsommationResponse;
import com.example.service.ConsommationService;



@RestController
@RequestMapping("/api/consommations-electriques")
public class ConsommationController {

    private final ConsommationService service;

    public ConsommationController(ConsommationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsommationResponse create(
            @RequestBody ConsommationRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ConsommationResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ConsommationResponse getById(
            @PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/pompe/{pompeId}")
    public List<ConsommationResponse> getByPompe(
            @PathVariable String pompeId) {
        return service.findByPompeId(pompeId);
    }

    @PutMapping("/{id}")
    public ConsommationResponse update(
            @PathVariable String id,
            @RequestBody ConsommationRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
