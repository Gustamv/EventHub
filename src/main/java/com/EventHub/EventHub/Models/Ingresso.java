package com.EventHub.EventHub.Models;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoIngresso;
    private Double preco;
    private Integer quantidadeDisponivel;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToMany(mappedBy = "ingresso")
    private List<Pedido> pedidos;

    // Construtor
    public Ingresso() {
    }

    public Ingresso(String tipoIngresso, Double preco, Integer quantidadeDisponivel, Evento evento) {
        this.tipoIngresso = tipoIngresso;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.evento = evento;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // hashCode e equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((tipoIngresso == null) ? 0 : tipoIngresso.hashCode());
        result = prime * result + ((preco == null) ? 0 : preco.hashCode());
        result = prime * result + ((quantidadeDisponivel == null) ? 0 : quantidadeDisponivel.hashCode());
        result = prime * result + ((evento == null) ? 0 : evento.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Ingresso other = (Ingresso) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(tipoIngresso, other.tipoIngresso) &&
                Objects.equals(preco, other.preco) &&
                Objects.equals(quantidadeDisponivel, other.quantidadeDisponivel) &&
                Objects.equals(evento, other.evento);
    }
}