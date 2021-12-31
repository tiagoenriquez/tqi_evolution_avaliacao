package com.tiagoenriquez.tqi_evolution_avaliacao.repositories;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public Optional<Cliente> findByEmail(String email);

}
