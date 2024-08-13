package com.EventHub.EventHub.Services;

import com.EventHub.EventHub.Models.Organizador;
import com.EventHub.EventHub.Repositories.OrganizadorRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    /**
     * Salva um novo organizador no banco de dados.
     *
     * @param organizador O organizador a ser salvo.
     * @return O organizador salvo.
     */
    public Organizador salvarOrganizador(Organizador organizador) {
        return organizadorRepository.save(organizador);
    }

    /**
     * Busca um organizador pelo e-mail.
     *
     * @param email O e-mail do organizador.
     * @return Um Optional contendo o organizador encontrado, ou Optional.empty()
     *         caso não seja encontrado.
     */
    public Optional<Organizador> buscarOrganizadorPorEmail(String email) {
        return Optional.ofNullable(organizadorRepository.findByEmail(email));
    }

    /**
     * Busca um organizador pelo ID.
     *
     * @param id O ID do organizador.
     * @return Um Optional contendo o organizador encontrado, ou Optional.empty()
     *         caso não seja encontrado.
     */
    public Optional<Organizador> buscarOrganizadorPorId(Long id) {
        return organizadorRepository.findById(id);
    }

    /**
     * Atualiza um organizador existente.
     *
     * @param id          O ID do organizador a ser atualizado.
     * @param organizador O organizador com os dados atualizados.
     * @return O organizador atualizado.
     * @throws EntityNotFoundException Caso o organizador com o ID informado não
     *                                 seja encontrado.
     */
    public Organizador atualizarOrganizador(Long id, Organizador organizador) throws EntityNotFoundException {
        Organizador existingOrganizador = organizadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organizador não encontrado"));
        existingOrganizador.setNome(organizador.getNome());
        existingOrganizador.setEmail(organizador.getEmail());
        // Atualize outros campos conforme necessário
        return organizadorRepository.save(existingOrganizador);
    }

    /**
     * Deleta um organizador pelo ID.
     *
     * @param id O ID do organizador a ser deletado.
     */
    public void deletarOrganizador(Long id) {
        organizadorRepository.deleteById(id);
    }
}
