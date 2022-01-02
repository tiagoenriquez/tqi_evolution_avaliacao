package com.tiagoenriquez.tqi_evolution_avaliacao.controllers;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimo;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimoDTO;
import com.tiagoenriquez.tqi_evolution_avaliacao.services.SolicitacaoDeEmprestimoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "api/solicitacoes-de-emprestimo")
public class SolicitacaoDeEmprestimoController {

    private SolicitacaoDeEmprestimoService solicitacaoDeEmprestimoService;

    public SolicitacaoDeEmprestimoController(SolicitacaoDeEmprestimoService solicitacaoDeEmprestimoService) {
        this.solicitacaoDeEmprestimoService = solicitacaoDeEmprestimoService;
    }

    @PostMapping(value = "/cadastro", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolicitacaoDeEmprestimo> inserir(@RequestBody SolicitacaoDeEmprestimoDTO dto) throws Exception {
        return solicitacaoDeEmprestimoService.inserir(dto);
    }

}
