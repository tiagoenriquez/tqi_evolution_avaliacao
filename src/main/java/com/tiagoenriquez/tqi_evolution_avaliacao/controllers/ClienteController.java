package com.tiagoenriquez.tqi_evolution_avaliacao.controllers;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import com.tiagoenriquez.tqi_evolution_avaliacao.services.ClienteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping(value = "/inserir", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        return clienteService.inserir(cliente);
    }

}
