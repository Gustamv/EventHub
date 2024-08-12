package com.EventHub.EventHub.Repositories;

import com.EventHub.EventHub.Models.Evento;
import com.EventHub.EventHub.Models.Organizador;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    // Busca por nome, ignorando case
    List<Evento> findByNomeContainingIgnoreCase(String nome);

    // Encontrar eventos com data e hora de início posterior a uma data específica
    List<Evento> findByDataHoraInicioAfter(LocalDateTime dataHoraInicio);

    // Encontrar eventos por categoria (ex.: show, palestra, etc.)
    List<Evento> findByCategoria(String categoria);

    // Buscar eventos por organizador
    List<Evento> findByOrganizador(Organizador organizador);
}
