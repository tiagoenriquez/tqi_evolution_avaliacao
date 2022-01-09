package com.tiagoenriquez.tqi_evolution_avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Contem os atributos das solicitações de empréstimo que retornam ao cliente na requisição de listagem.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoDeEmprestimoDTOListagem {

    private Integer idSolicitacaoDeEmprestimo;

    private BigDecimal valorDoEmprestimo;

    private Integer quantidadeDeParcelas;

}
