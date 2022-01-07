package com.tiagoenriquez.tqi_evolution_avaliacao.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SolicitacaoDeEmprestimoDTOCadastro {

    private BigDecimal valorDoEmprestimo;

    private LocalDate dataDaPrimeiraParcela;

    private Integer quantidadeDeParcelas;

}
