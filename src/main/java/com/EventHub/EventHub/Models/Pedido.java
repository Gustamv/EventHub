package com.EventHub.EventHub.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participante_id")
    private Participante participante;

    @ManyToOne
    @JoinColumn(name = "ingresso_id")
    private Ingresso ingresso;

    private LocalDateTime dataCompra;
    private String status;

    // Construtor
    public Pedido() {
    }

    public Pedido(Participante participante, Ingresso ingresso, LocalDateTime dataCompra, String status) {
        this.participante = participante;
        this.ingresso = ingresso;
        this.dataCompra = dataCompra;
        this.status = status;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Métodos de override
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((participante == null) ? 0 : participante.hashCode());
        result = prime * result + ((ingresso == null) ? 0 : ingresso.hashCode());
        result = prime * result + ((dataCompra == null) ? 0 : dataCompra.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (participante == null) {
            if (other.participante != null)
                return false;
        } else if (!participante.equals(other.participante))
            return false;
        if (ingresso == null) {
            if (other.ingresso != null)
                return false;
        } else if (!ingresso.equals(other.ingresso))
            return false;
        if (dataCompra == null) {
            if (other.dataCompra != null)
                return false;
        } else if (!dataCompra.equals(other.dataCompra))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }
}