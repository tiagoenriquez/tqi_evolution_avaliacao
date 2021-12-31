package com.tiagoenriquez.tqi_evolution_avaliacao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "solicitacoesDeEmprestimo")
public class SolicitacaoDeEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitacaoDeEmprestimo;

    private BigDecimal valorDoEmprestimo;

    private Date dataDaPrimeiraParcela;

    private Integer quantidadeDeParcelas;

    @MapsId("idCliente")
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

}
