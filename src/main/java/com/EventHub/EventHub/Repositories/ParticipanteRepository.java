package com.EventHub.EventHub.Repositories;

import com.EventHub.EventHub.Models.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Participante findByEmail(String email);
}
