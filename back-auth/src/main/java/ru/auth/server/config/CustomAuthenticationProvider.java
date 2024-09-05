package ru.auth.server.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // authentication logic here
        String username = authentication.getName();
        String password = String.valueOf(
                authentication.getCredentials());
        if ("john".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    Arrays.asList(
                            new SimpleGrantedAuthority("read")
                    ));
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        // type of the Authentication implementation here
        return UsernamePasswordAuthenticationToken.class.
                isAssignableFrom(authenticationType);
    }

}
