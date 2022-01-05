package com.tiagoenriquez.tqi_evolution_avaliacao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoDeEmprestimoDTOListagem {

    private Integer idSolicitacaoDeEmprestimo;

    private BigDecimal valorDoEmprestimo;

    private Integer quantidadeDeParcelas;

}
