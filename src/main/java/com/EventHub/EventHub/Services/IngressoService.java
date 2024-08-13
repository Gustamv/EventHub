package com.EventHub.EventHub.Services;

import com.EventHub.EventHub.Models.Evento;
import com.EventHub.EventHub.Models.Ingresso;
import com.EventHub.EventHub.Repositories.IngressoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IngressoService {
    private final IngressoRepository ingressoRepository;

    public IngressoService(IngressoRepository ingressoRepository) {
        this.ingressoRepository = ingressoRepository;
    }

    public List<Ingresso> findAll() {
        return ingressoRepository.findAll();
    }

    public Optional<Ingresso> findById(Long id) {
        return ingressoRepository.findById(id);
    }

    public Ingresso save(Ingresso ingresso) {
        return ingressoRepository.save(ingresso);
    }

    public void delete(Ingresso ingresso) {
        ingressoRepository.delete(ingresso);
    }

    public Ingresso createIngresso(String tipoIngresso, Double preco, Integer quantidadeDisponivel,
            LocalDate dataValidade, Evento evento) {
        Ingresso ingresso = new Ingresso(tipoIngresso, preco, quantidadeDisponivel, dataValidade, evento);
        return save(ingresso);
    }

    public Ingresso updateIngresso(Long id, String tipoIngresso, Double preco, Integer quantidadeDisponivel,
            LocalDate dataValidade, Evento evento) {
        Ingresso ingresso = findById(id).orElseThrow();
        ingresso.setTipoIngresso(tipoIngresso);
        ingresso.setPreco(preco);
        ingresso.setQuantidadeDisponivel(quantidadeDisponivel);
        ingresso.setDataValidade(dataValidade);
        ingresso.setEvento(evento);
        return save(ingresso);
    }
}