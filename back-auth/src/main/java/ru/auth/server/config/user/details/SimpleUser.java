package ru.auth.server.config.user.details;

import org.apache.logging.log4j.util.Base64Util;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SimpleUser implements UserDetails {
    private final String username;
    private final String password;

    public SimpleUser(String username, String password) {
        User.UserBuilder builder1 = User.withUsername("bill");
        UserDetails u1 = builder1
                .password("12345")
                .authorities("read", "write")
                .passwordEncoder(Base64Util::encode)
                .accountExpired(false)
                .disabled(true)
                .build();
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }
// Omitted code
}
