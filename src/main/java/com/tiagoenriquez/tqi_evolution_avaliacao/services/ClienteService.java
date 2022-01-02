package com.tiagoenriquez.tqi_evolution_avaliacao.services;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import com.tiagoenriquez.tqi_evolution_avaliacao.repositories.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    public ClienteService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retorna o cliente logado.
     * @return
     */
    public Cliente clienteLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
        return clienteRepository.findByEmail(email).get();
    }

    /**
     * Cadastra um cliente novo no banco de dados.
     * @param cliente
     * @return
     */
    public ResponseEntity<Cliente> inserir(Cliente cliente) {
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

}
