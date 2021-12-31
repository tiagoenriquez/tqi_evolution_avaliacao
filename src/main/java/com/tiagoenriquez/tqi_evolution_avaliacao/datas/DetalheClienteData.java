package com.tiagoenriquez.tqi_evolution_avaliacao.datas;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheClienteData implements UserDetails {

    private final Optional<Cliente> optional;

    public DetalheClienteData(Optional<Cliente> optional) {
        this.optional = optional;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return optional.orElse(new Cliente()).getSenha();
    }

    @Override
    public String getUsername() {
        return optional.orElse(new Cliente()).getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
