package com.tiagoenriquez.tqi_evolution_avaliacao.data;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe com dados do usuário nos moldes do Spring Security. Por padrão, os dados requeridos são nome de usuário
 * (username) e senha (password). Neste projeto, adaptei para conseguir validar por e-mail e senha.
 */
public class DetalheClienteData implements UserDetails {

    private final Optional<Cliente> optional;

    public DetalheClienteData(Optional<Cliente> optional) {
        this.optional = optional;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    /**
     * Retorna a senha de um cliente existente ou um protótipo de cliente com senha vazia.
     * @return
     */
    @Override
    public String getPassword() {
        return optional.orElse(new Cliente()).getSenha();
    }

    /**
     * Retorna o e-mail de um cliente existente ou um protótipo de cliente com e-mail vazio.
     * @return
     */
    @Override
    public String getUsername() {
        return optional.orElse(new Cliente()).getEmail();
    }

    /**
     * Permite uso do sistema por cliente com conta não expirada.
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Permite uso do sistema por cliente com conta não bloqueada.
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Permite uso do sistema por cliente com credenciais não expiradas.
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Permite uso do sistema por cliente ativo.
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
