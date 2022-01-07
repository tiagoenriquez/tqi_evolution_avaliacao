package com.tiagoenriquez.tqi_evolution_avaliacao.controllers;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.Erro;
import com.tiagoenriquez.tqi_evolution_avaliacao.services.ClienteService;
import org.springframework.http.HttpStatus;
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
     * Recebe dados de cliente via requisição HTTP e, se logado, envia-os para a classe de serviço.
     * @param cliente
     * @return
     */
    @PostMapping(value = "/cadastro", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity inserir(@RequestBody Cliente cliente) throws Exception {
        try {
            return ResponseEntity.ok(clienteService.inserir(cliente));
        } catch (Exception exception) {
            return new ResponseEntity(new Erro(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
