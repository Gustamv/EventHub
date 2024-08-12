package com.EventHub.EventHub.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHub.EventHub.Models.Ingresso;
import com.EventHub.EventHub.Models.Participante;
import com.EventHub.EventHub.Models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Encontrar pedidos por participante
    List<Pedido> findByParticipante(Participante participante);

    // Encontrar pedidos por ingresso
    List<Pedido> findByIngresso(Ingresso ingresso);

    // Encontrar pedidos por status
    List<Pedido> findByStatus(String status);

    // Encontrar pedidos por participante e status (busca combinada)
    List<Pedido> findByParticipanteAndStatus(Participante participante, String status);

    // Encontrar todos os pedidos entre duas datas (inclusive)
    List<Pedido> findByDataCompraBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
}
