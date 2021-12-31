package com.tiagoenriquez.tqi_evolution_avaliacao.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtValidarFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATRIBUTO = "Authorization";
    public static final String ATRIBUTO_PREFIXO = "Bearer ";

    public JwtValidarFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String atributo = request.getHeader(HEADER_ATRIBUTO);
        if (atributo == null || !atributo.startsWith(ATRIBUTO_PREFIXO)) {
            chain.doFilter(request, response);
            return;
        }
        String token = atributo.replace(ATRIBUTO_PREFIXO, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAutheticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAutheticationToken(String token) {
            String cliente = JWT.require(Algorithm.HMAC512(new JwtSecret().getSecret()))
                .build()
                .verify(token)
                .getSubject();
        if (cliente == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(cliente, null, new ArrayList<>());
    }

}
