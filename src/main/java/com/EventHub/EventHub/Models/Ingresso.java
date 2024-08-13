package com.EventHub.EventHub.Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
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

    @Column(nullable = false, length = 50)
    private String tipoIngresso;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidadeDisponivel;

    @Column(nullable = false)
    private LocalDate dataValidade;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToMany(mappedBy = "ingresso")
    private List<Pedido> pedidos;

    // Construtor padrão
    public Ingresso() {
    }

    // Construtor com parâmetros
    public Ingresso(String tipoIngresso, Double preco, Integer quantidadeDisponivel, LocalDate dataValidade,
            Evento evento) {
        this.tipoIngresso = tipoIngresso;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.dataValidade = dataValidade;
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

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
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
        return Objects.hash(id, tipoIngresso, preco, quantidadeDisponivel, dataValidade, evento);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ingresso other = (Ingresso) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(tipoIngresso, other.tipoIngresso) &&
                Objects.equals(preco, other.preco) &&
                Objects.equals(quantidadeDisponivel, other.quantidadeDisponivel) &&
                Objects.equals(dataValidade, other.dataValidade) &&
                Objects.equals(evento, other.evento);
    }
}