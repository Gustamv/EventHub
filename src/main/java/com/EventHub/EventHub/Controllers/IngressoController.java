package com.EventHub.EventHub.Controllers;

import com.EventHub.EventHub.Models.Ingresso;
import com.EventHub.EventHub.Services.IngressoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingressos")
public class IngressoController {
    private final IngressoService ingressoService;

    public IngressoController(IngressoService ingressoService) {
        this.ingressoService = ingressoService;
    }

    @GetMapping
    public ResponseEntity<List<Ingresso>> getAllIngressos() {
        List<Ingresso> ingressos = ingressoService.findAll();
        return new ResponseEntity<>(ingressos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingresso> getIngressoById(@PathVariable Long id) {
        Optional<Ingresso> ingresso = ingressoService.findById(id);
        return ingresso.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ingresso> createIngresso(@RequestBody Ingresso ingresso) {
        Ingresso newIngresso = ingressoService.createIngresso(
                ingresso.getTipoIngresso(),
                ingresso.getPreco(),
                ingresso.getQuantidadeDisponivel(),
                ingresso.getDataValidade(),
                ingresso.getEvento());
        return new ResponseEntity<>(newIngresso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingresso> updateIngresso(@PathVariable Long id, @RequestBody Ingresso ingresso) {
        Ingresso updatedIngresso = ingressoService.updateIngresso(
                id,
                ingresso.getTipoIngresso(),
                ingresso.getPreco(),
                ingresso.getQuantidadeDisponivel(),
                ingresso.getDataValidade(),
                ingresso.getEvento());
        return new ResponseEntity<>(updatedIngresso, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngresso(@PathVariable Long id) {
        ingressoService.delete(ingressoService.findById(id).orElseThrow());
        return ResponseEntity.noContent().build();
    }
}