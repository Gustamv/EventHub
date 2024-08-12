package com.EventHub.EventHub.Repositories;

import com.EventHub.EventHub.Models.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {

    // Encontrar organizador por email
    Organizador findByEmail(String email);
}
