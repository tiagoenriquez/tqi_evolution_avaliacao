package com.tiagoenriquez.tqi_evolution_avaliacao.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SolicitacaoDeEmprestimoDTO {

    private BigDecimal valorDoEmprestimo;

    private LocalDate dataDaPrimeiraParcela;

    private Integer quantidadeDeParcelas;

}
