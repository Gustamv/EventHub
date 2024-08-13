package com.EventHub.EventHub.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHub.EventHub.Models.Evento;
import com.EventHub.EventHub.Models.Organizador;
import com.EventHub.EventHub.Repositories.EventoRepository;
import com.EventHub.EventHub.Repositories.OrganizadorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private OrganizadorRepository organizadorRepository;

    public List<Evento> buscarTodosOsEventos() {
        return eventoRepository.findAll();
    }

    public List<Evento> buscarEventosPorNome(String nome) {
        return eventoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Evento> buscarEventosPorDataHoraInicioDepois(LocalDateTime dataHoraInicio) {
        return eventoRepository.findByDataHoraInicioAfter(dataHoraInicio);
    }

    public List<Evento> buscarEventosPorCategoria(String categoria) {
        return eventoRepository.findByCategoria(categoria);
    }

    public List<Evento> buscarEventosPorOrganizador(Organizador organizador) {
        return eventoRepository.findByOrganizador(organizador);
    }

    public Evento criarEvento(Evento evento) {
        // Validações:
        if (evento.getNome() == null || evento.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do evento é obrigatório.");
        }
        if (evento.getDataHoraInicio().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de início do evento não pode ser no passado.");
        }
        if (!organizadorRepository.existsById(evento.getOrganizador().getId())) {
            throw new EntityNotFoundException("Organizador não encontrado.");
        }
        if (verificarConflitoDeHorarios(evento)) {
            throw new IllegalArgumentException("Já existe um evento agendado para este horário.");
        }

        return eventoRepository.save(evento);
    }

    public Evento atualizarEvento(Long id, Evento evento) {
        Evento eventoExistente = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));

        // Atualizar apenas os campos desejados
        eventoExistente.setNome(evento.getNome());
        eventoExistente.setDataHoraInicio(evento.getDataHoraInicio());
        // ... outros campos

        return eventoRepository.save(eventoExistente);
    }

    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    private boolean verificarConflitoDeHorarios(Evento evento) {
        // Obtenha a lista de eventos do mesmo organizador (substitua pela sua lógica)
        List<Evento> eventosExistentes = eventoRepository.findByOrganizador(evento.getOrganizador());

        // Itere sobre os eventos existentes
        for (Evento eventoExistente : eventosExistentes) {
            // Verifique se os intervalos de tempo se sobrepõem
            if (evento.getDataHoraInicio().isBefore(eventoExistente.getDataHoraTermino()) &&
                    evento.getDataHoraTermino().isAfter(eventoExistente.getDataHoraInicio())) {
                return true; // Há conflito de horários
            }
        }

        return false; // Não há conflito
    }

}