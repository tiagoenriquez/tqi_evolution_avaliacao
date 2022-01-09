package com.tiagoenriquez.tqi_evolution_avaliacao.repositories;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface responsável por persistir e recuperar dados de clientes no banco de dados.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * Procura um cliente pelo e-mail.
     *
     * @param email
     * @return
     */
    public Optional<Cliente> findByEmail(String email);

}
