package com.EventHub.EventHub.Services;

import com.EventHub.EventHub.Models.Participante;
import com.EventHub.EventHub.Repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipanteService {

    // Injeta a instância do repositório de participantes
    @Autowired
    private ParticipanteRepository participanteRepository;

    /**
     * Cria um novo participante
     * 
     * @param participante o participante a ser criado
     * @return o participante criado
     * @throws Exception
     */
    public Participante criarParticipante(Participante participante) throws Exception {
        if (buscarParticipantePorEmail(participante.getEmail()).isPresent()) {
            throw new Exception("Participante com e-mail já existe");
        }
        return salvarParticipante(participante);
    }

    /**
     * Salva um novo participante ou atualiza um existente
     * 
     * @param participante o participante a ser salvo
     * @return o participante salvo
     */
    public Participante salvarParticipante(Participante participante) {
        return participanteRepository.save(participante);
    }

    /**
     * Busca um participante por e-mail
     * 
     * @param email o e-mail do participante
     * @return um Optional com o participante encontrado, ou vazio se não encontrado
     */
    public Optional<Participante> buscarParticipantePorEmail(String email) {
        return Optional.ofNullable(participanteRepository.findByEmail(email));
    }

    /**
     * Busca um participante por ID
     * 
     * @param id o ID do participante
     * @return um Optional com o participante encontrado, ou vazio se não encontrado
     */
    public Optional<Participante> buscarParticipantePorId(Long id) {
        return participanteRepository.findById(id);
    }

    /**
     * Atualiza um participante existente
     * 
     * @param id           o ID do participante a ser atualizado
     * @param participante o participante com os dados atualizados
     * @return o participante atualizado
     */
    public Participante atualizarParticipante(Long id, Participante participante) {
        Participante existingParticipante = participanteRepository.findById(id).orElseThrow();
        existingParticipante.setNome(participante.getNome());
        existingParticipante.setEmail(participante.getEmail());
        existingParticipante.setSenha(participante.getSenha());
        // Atualize outros campos como necessário
        return participanteRepository.save(existingParticipante);
    }

    /**
     * Deleta um participante por ID
     * 
     * @param id o ID do participante a ser deletado
     */
    public void deletarParticipante(Long id) {
        participanteRepository.deleteById(id);
    }
}