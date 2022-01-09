package com.tiagoenriquez.tqi_evolution_avaliacao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe que determina quais informações sobre solicitação de empréstimo devem ser guardadas e recuperadas no sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "solicitacoesDeEmprestimo")
public class SolicitacaoDeEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitacaoDeEmprestimo;

    private BigDecimal valorDoEmprestimo;

    private LocalDate dataDaPrimeiraParcela;

    private Integer quantidadeDeParcelas;

    private Integer idCliente;

    @MapsId("idCliente")
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

}
