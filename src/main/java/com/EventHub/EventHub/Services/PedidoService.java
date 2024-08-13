package com.EventHub.EventHub.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHub.EventHub.Models.Participante;
import com.EventHub.EventHub.Models.Pedido;
import com.EventHub.EventHub.Repositories.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Métodos para buscar pedidos
    public List<Pedido> buscarPedidosPorParticipante(Participante participante) {
        return pedidoRepository.findByParticipante(participante);
    }

    // ... outros métodos de busca

    // Método para criar um novo pedido
    public Pedido criarPedido(Pedido pedido) {
        // Validações (ex: verificar se o ingresso está disponível, se o participante já
        // possui um pedido para o mesmo evento, etc.)
        return pedidoRepository.save(pedido);
    }

    // Método para atualizar um pedido
    public Pedido atualizarPedido(Long id, Pedido pedido) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        // Atualizar os campos do pedido
        pedidoExistente.setStatus(pedido.getStatus());
        // ... outros campos a serem atualizados

        return pedidoRepository.save(pedidoExistente);
    }

    // Método para cancelar um pedido
    public void cancelarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        // Implementar a lógica de cancelamento (ex: atualizar o status para
        // "cancelado", liberar ingressos)
        pedido.setStatus("CANCELADO");
        pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarPedidos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPedidos'");
    }

    public Pedido buscarPedidoPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPedidoPorId'");
    }
}
