package com.tiagoenriquez.tqi_evolution_avaliacao.security;

/*import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;*/

/*@Component*/
public class JwtSecret {

    /*@Value("${jwt.secret}")*/
    private String secret = "";

    public String getSecret() {
        return this.secret;
    }

}
