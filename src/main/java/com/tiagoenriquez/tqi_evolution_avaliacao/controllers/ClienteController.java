package com.tiagoenriquez.tqi_evolution_avaliacao.controllers;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import com.tiagoenriquez.tqi_evolution_avaliacao.services.ClienteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Cadastra um cliente novo no banco de dados.
     * @param cliente
     * @return
     */
    @PostMapping(value = "/cadastro", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        return clienteService.inserir(cliente);
    }

}
