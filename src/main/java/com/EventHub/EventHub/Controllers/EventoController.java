package com.EventHub.EventHub.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventHub.EventHub.Models.Evento;
import com.EventHub.EventHub.Models.Organizador;
import com.EventHub.EventHub.Services.EventoService;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> buscarTodosOsEventos() {
        return new ResponseEntity<>(eventoService.buscarTodosOsEventos(), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Evento>> buscarEventosPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(eventoService.buscarEventosPorNome(nome), HttpStatus.OK);
    }

    @GetMapping("/data-hora-inicio-depois/{dataHoraInicio}")
    public ResponseEntity<List<Evento>> buscarEventosPorDataHoraInicioDepois(
            @PathVariable LocalDateTime dataHoraInicio) {
        return new ResponseEntity<>(eventoService.buscarEventosPorDataHoraInicioDepois(dataHoraInicio), HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Evento>> buscarEventosPorCategoria(@PathVariable String categoria) {
        return new ResponseEntity<>(eventoService.buscarEventosPorCategoria(categoria), HttpStatus.OK);
    }

    @GetMapping("/organizador/{organizador}")
    public ResponseEntity<List<Evento>> buscarEventosPorOrganizador(@PathVariable Organizador organizador) {
        return new ResponseEntity<>(eventoService.buscarEventosPorOrganizador(organizador), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        return new ResponseEntity<>(eventoService.criarEvento(evento), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        return new ResponseEntity<>(eventoService.atualizarEvento(id, evento), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        eventoService.deletarEvento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}