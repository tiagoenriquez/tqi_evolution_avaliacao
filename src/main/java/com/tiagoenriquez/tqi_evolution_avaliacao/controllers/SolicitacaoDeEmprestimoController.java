package com.tiagoenriquez.tqi_evolution_avaliacao.controllers;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimo;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimoDTOCadastro;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimoDTOListagem;
import com.tiagoenriquez.tqi_evolution_avaliacao.services.SolicitacaoDeEmprestimoService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<SolicitacaoDeEmprestimo> inserir(@RequestBody SolicitacaoDeEmprestimoDTOCadastro dto) throws Exception {
        return solicitacaoDeEmprestimoService.inserir(dto);
    }

    /**
     * Retorna todas as solicitações de empréstimo feitas pelo cliente logado.
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/todas-do-cliente-logado")
    public ResponseEntity<List<SolicitacaoDeEmprestimoDTOListagem>> listar() throws Exception {
        return solicitacaoDeEmprestimoService.listar();
    }

    /**
     * Recebe um id de solicitação de empréstimo via param e retorna os detalhes dessa solcitação.
     * @param id
     * @return
     */
    @GetMapping(value = "/detalhes/{id}")
    public ResponseEntity<SolicitacaoDeEmprestimo> mostrarUma(@PathVariable Integer id) throws Exception {
        return solicitacaoDeEmprestimoService.mostrarUma(id);
    }

}
