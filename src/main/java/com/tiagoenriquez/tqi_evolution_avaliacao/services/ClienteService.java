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
     *
     * @param cliente
     * @return
     * @throws Exception
     */
    public ResponseEntity<Cliente> inserir(Cliente cliente) throws Exception {
        try {
            cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
            return ResponseEntity.ok(clienteRepository.save(cliente));
        } catch (Exception exception) {
            throw new Exception("Erro ao tentar salvar o cliente no banco de dados: " + exception.getMessage());
        }
    }

}
