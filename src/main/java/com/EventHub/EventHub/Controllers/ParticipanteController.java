package com.EventHub.EventHub.Controllers;

import com.EventHub.EventHub.Models.Participante;
import com.EventHub.EventHub.Services.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<Participante> criarParticipante(@RequestBody Participante participante) throws Exception {
        Participante participanteSalvo = participanteService.criarParticipante(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> buscarParticipantePorId(@PathVariable Long id) {
        return participanteService.buscarParticipantePorId(id)
                .map(participante -> ResponseEntity.ok(participante))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> atualizarParticipante(@PathVariable Long id,
            @RequestBody Participante participante) {
        Participante participanteAtualizado = participanteService.atualizarParticipante(id, participante);
        return ResponseEntity.ok(participanteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParticipante(@PathVariable Long id) {
        participanteService.deletarParticipante(id);
        return ResponseEntity.noContent().build();
    }
}
