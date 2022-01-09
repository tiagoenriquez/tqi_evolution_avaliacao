package com.tiagoenriquez.tqi_evolution_avaliacao.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe que determina as informações sobre erro enviadas ao endpoint.
 */
@Data
@AllArgsConstructor
public class Erro {

    private String message;

}
