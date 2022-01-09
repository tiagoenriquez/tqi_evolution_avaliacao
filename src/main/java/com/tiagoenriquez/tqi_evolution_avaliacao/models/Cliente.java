package com.tiagoenriquez.tqi_evolution_avaliacao.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Classe que determina os atributos do cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clientes")
@Embeddable
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "rg", unique = true)
    private String rg;

    private String endereco;

    private BigDecimal renda;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

}
