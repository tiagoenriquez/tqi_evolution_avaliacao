package com.tiagoenriquez.tqi_evolution_avaliacao.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;

//@Component
@ConfigurationProperties("jwt.secret")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtSecret {

    //@Value("${jwt.secret}")
    private String secret;

}
