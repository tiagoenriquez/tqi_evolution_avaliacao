package com.tiagoenriquez.tqi_evolution_avaliacao.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Contém atributos das solicitações de empréstimo que os clientes informam durante o cadastro da solicitação.
 */
@Data
public class SolicitacaoDeEmprestimoDTOCadastro {

    private BigDecimal valorDoEmprestimo;

    private LocalDate dataDaPrimeiraParcela;

    private Integer quantidadeDeParcelas;

}
