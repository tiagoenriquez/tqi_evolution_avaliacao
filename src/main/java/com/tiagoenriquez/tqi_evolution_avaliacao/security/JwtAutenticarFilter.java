package com.tiagoenriquez.tqi_evolution_avaliacao.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagoenriquez.tqi_evolution_avaliacao.data.DetalheClienteData;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Autentica o cliente gerando o token JWT.
 */
public class JwtAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRACAO = 600_000;

    private final AuthenticationManager authenticationManager;

    public JwtAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Autentica o cliente. Recebe uma requisição com e-mail e senha e retorna uma autenticação de cliente.
     * @param request
     * @param response
     * @return
     * @throws RuntimeException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws RuntimeException {
        try {
            Cliente cliente = new ObjectMapper().readValue(request.getInputStream(), Cliente.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    cliente.getEmail(),
                    cliente.getSenha(),
                    new ArrayList<>()

            ));
        } catch (IOException ioException) {
            throw new RuntimeException("Falha ao autenticar o cliente: " + ioException);
        }
    }

    /**
     * Cria o token.
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        DetalheClienteData detalheClienteData = (DetalheClienteData) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(detalheClienteData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(new JwtSecret().getSecret()));
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
