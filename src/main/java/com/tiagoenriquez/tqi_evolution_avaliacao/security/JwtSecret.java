package com.tiagoenriquez.tqi_evolution_avaliacao.security;

public class JwtSecret {

    /**
     * Valor obtido da variável de ambiente.
     */
    private static String SECRET = "${JWT_SECRET}";

    public String getSecret() {
        return this.SECRET;
    }

}
