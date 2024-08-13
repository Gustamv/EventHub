package com.EventHub.EventHub.Controllers;

import com.EventHub.EventHub.Models.Organizador;
import com.EventHub.EventHub.Services.OrganizadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {
    @Autowired
    private OrganizadorService service;

    @GetMapping
    public ResponseEntity<List<Organizador>> getAllOrganizadores() {
        List<Organizador> organizadores = service.findAll();
        return ResponseEntity.ok(organizadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizador> getOrganizadorById(@PathVariable Long id) {
        Organizador organizador = service.findById(id);
        return ResponseEntity.ok(organizador);
    }

    @PostMapping
    public ResponseEntity<Organizador> createOrganizador(@RequestBody Organizador organizador) {
        try {
            Organizador novoOrganizador = service.save(organizador);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoOrganizador);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizador> updateOrganizador(@PathVariable Long id, @RequestBody Organizador organizador) {
        Organizador updatedOrganizador = service.updateOrganizador(id, organizador);
        return ResponseEntity.ok(updatedOrganizador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizador(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}