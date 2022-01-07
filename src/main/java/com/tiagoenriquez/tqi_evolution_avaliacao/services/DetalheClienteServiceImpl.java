package com.tiagoenriquez.tqi_evolution_avaliacao.services;

import com.tiagoenriquez.tqi_evolution_avaliacao.data.DetalheClienteData;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import com.tiagoenriquez.tqi_evolution_avaliacao.repositories.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheClienteServiceImpl implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public DetalheClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> optional = clienteRepository.findByEmail(email);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("Cliente não encontrado");
        }
        return new DetalheClienteData(optional);
    }

}
