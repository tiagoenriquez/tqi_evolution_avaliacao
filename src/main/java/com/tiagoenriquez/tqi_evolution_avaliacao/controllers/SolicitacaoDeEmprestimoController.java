package com.tiagoenriquez.tqi_evolution_avaliacao.controllers;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Erro;
import com.tiagoenriquez.tqi_evolution_avaliacao.dto.SolicitacaoDeEmprestimoDTOCadastro;
import com.tiagoenriquez.tqi_evolution_avaliacao.services.SolicitacaoDeEmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/solicitacoes-de-emprestimo")
public class SolicitacaoDeEmprestimoController {

    private SolicitacaoDeEmprestimoService solicitacaoDeEmprestimoService;

    public SolicitacaoDeEmprestimoController(SolicitacaoDeEmprestimoService solicitacaoDeEmprestimoService) {
        this.solicitacaoDeEmprestimoService = solicitacaoDeEmprestimoService;
    }

    /**
     * Recebe informações de solicitação de empréstimo via requisição HTTP e as envia para a classe de serviço verificar
     * a validade da requisição e, se aprovar, salvar.
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/cadastro", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity inserir(@RequestBody SolicitacaoDeEmprestimoDTOCadastro dto) throws Exception {
        try {
            return solicitacaoDeEmprestimoService.inserir(dto);
        } catch (Exception exception) {
            return new ResponseEntity(new Erro(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retorna uma lista das solicitações de empréstimo feitas pelo cliente logado com as seguintes informações: id;
     * valor do empréstimo; e quantidade de parcelas.
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/todas-do-cliente-logado")
    public ResponseEntity listar() throws Exception {
        try {
            return solicitacaoDeEmprestimoService.listar();
        } catch (Exception exception) {
            return new ResponseEntity(new Erro(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Recebe um id de solicitação de empréstimo via param e retorna os detalhes dessa solcitação se esta tiver sido
     * feira pelo cliente logado.
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/detalhes/{id}")
    public ResponseEntity mostrarUma(@PathVariable Integer id) throws Exception {
        try {
            return solicitacaoDeEmprestimoService.mostrarUma(id);
        } catch (Exception exception) {
            return new ResponseEntity(new Erro(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
