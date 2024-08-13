package com.EventHub.EventHub.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHub.EventHub.Models.Evento;
import com.EventHub.EventHub.Models.Ingresso;
import com.EventHub.EventHub.Repositories.IngressoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    // Encontrar ingressos por evento
    public List<Ingresso> buscarIngressosPorEvento(Evento evento) {
        return ingressoRepository.findByEvento(evento);
    }

    // Encontrar ingressos por tipo
    public List<Ingresso> buscarIngressosPorTipo(String tipoIngresso) {
        return ingressoRepository.findByTipoIngresso(tipoIngresso);
    }

    // Encontrar ingressos com quantidade disponível maior que um determinado valor
    public List<Ingresso> buscarIngressosDisponiveis(Integer quantidadeMinima) {
        return ingressoRepository.findByQuantidadeDisponivelGreaterThan(quantidadeMinima);
    }

    // Criar um novo ingresso
    public Ingresso criarIngresso(Ingresso ingresso) {
        return ingressoRepository.save(ingresso);
    }

    // Atualizar um ingresso
    public Ingresso atualizarIngresso(Long id, Ingresso ingresso) {
        Ingresso ingressoExistente = ingressoRepository.findById(id).orElse(null);
        if (ingressoExistente == null) {
            throw new EntityNotFoundException("Ingresso não encontrado");
        }

        // Atualizar os campos do ingresso
        ingressoExistente.setTipoIngresso(ingresso.getTipoIngresso());
        ingressoExistente.setQuantidadeDisponivel(ingresso.getQuantidadeDisponivel());
        // ... outros campos a serem atualizados

        return ingressoRepository.save(ingressoExistente);
    }

    // Vender um ingresso
    public void venderIngresso(Long id) {
        Ingresso ingresso = ingressoRepository.findById(id).orElse(null);
        if (ingresso == null) {
            throw new EntityNotFoundException("Ingresso não encontrado");
        }

        // Verificar se há ingressos disponíveis
        if (ingresso.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Ingresso indisponível");
        }

        // Atualizar a quantidade disponível
        ingresso.setQuantidadeDisponivel(ingresso.getQuantidadeDisponivel() - 1);
        ingressoRepository.save(ingresso);
    }
}
