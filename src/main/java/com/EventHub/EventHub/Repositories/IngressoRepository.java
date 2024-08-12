package com.EventHub.EventHub.Repositories;

import com.EventHub.EventHub.Models.Evento;
import com.EventHub.EventHub.Models.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    // Encontrar ingressos por evento
    List<Ingresso> findByEvento(Evento evento);

    // Encontrar ingressos por tipo (inteira, meia, etc.)
    List<Ingresso> findByTipoIngresso(String tipoIngresso);

    // Encontrar ingressos com quantidade disponível maior que um determinado valor
    List<Ingresso> findByQuantidadeDisponivelGreaterThan(Integer quantidadeDisponivel);
}
