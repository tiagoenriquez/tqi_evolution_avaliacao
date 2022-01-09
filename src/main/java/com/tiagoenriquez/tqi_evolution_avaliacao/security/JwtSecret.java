package com.tiagoenriquez.tqi_evolution_avaliacao.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por fornecer a senha para composição do token JWT.
 */
@Component
public class JwtSecret {

    /**
     * Valor obtido da variável de ambiente.
     */
    @Value("${jwt.secret}")
    private String secret;

    public String getSecret() {
        System.out.println("Segredo: " + secret);
        return this.secret;
    }

}
