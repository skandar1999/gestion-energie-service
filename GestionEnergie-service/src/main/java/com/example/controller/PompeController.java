package com.example.controller;

import java.util.List;
import java.util.Map;

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

import com.example.dto.PompeResponse;
import com.example.models.Pompe;
import com.example.service.PompeService;

@RestController
@RequestMapping("/api/pompes")
public class PompeController {

    private final PompeService service;

    public PompeController(PompeService service) {
        this.service = service;
    }

    @GetMapping
    public List<PompeResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PompeResponse getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public PompeResponse create(@RequestBody Pompe pompe) {
        return service.create(pompe);
    }

    @PutMapping("/{id}")
    public PompeResponse update(
            @PathVariable String id,
            @RequestBody Pompe pompe) {
        return service.update(id, pompe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
    
    @GetMapping("/{id}/disponibilite")
    public Map<String, Object> checkDisponibilite(@PathVariable String id) {
        PompeResponse pompe = service.findById(id);
        boolean disponible = pompe != null && "ON".equalsIgnoreCase(pompe.getStatut());
        
        return Map.of(
            "pompeId", id,
            "disponible", disponible,
            "statut", pompe != null ? pompe.getStatut() : "NOT_FOUND",
            "message", disponible ? "Pompe disponible" : "Pompe non disponible"
        );
    }

    @GetMapping("/{id}/exists")
    public Map<String, Boolean> exists(@PathVariable String id) {
        try {
            PompeResponse pompe = service.findById(id);
            return Map.of("exists", pompe != null);
        } catch (Exception e) {
            return Map.of("exists", false);
        }
    }
}
