package com.EventHub.EventHub.Services;

import com.EventHub.EventHub.Models.Organizador;
import com.EventHub.EventHub.Repositories.OrganizadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizadorService {
    @Autowired
    private OrganizadorRepository repository;

    public List<Organizador> findAll() {
        return repository.findAll();
    }

    public Organizador findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Organizador save(Organizador organizador) throws Exception {
        validateOrganizador(organizador);
        return repository.save(organizador);
    }

    public Organizador updateOrganizador(Long id, Organizador organizador) {
        Organizador existingOrganizador = findById(id);
        existingOrganizador.setNome(organizador.getNome());
        existingOrganizador.setEmail(organizador.getEmail());
        // ...
        return repository.save(existingOrganizador);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void validateOrganizador(Organizador organizador) throws Exception {
        if (organizador.getNome() == null || organizador.getNome().isEmpty()) {
            throw new Exception("Nome do organizador é obrigatório");
        }
        // ...
    }
}