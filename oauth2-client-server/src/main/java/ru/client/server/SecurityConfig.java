package ru.client.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ComponentScan
@Configuration
public class SecurityConfig {

    @Value("${client-id}")
    private String clientId;
    @Value("${client-secret}")
    private String clientSecret;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> c.anyRequest()
                .authenticated());
        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> clientRegistrations = new ArrayList<>();
        clientRegistrations.add(googleClientRegistration());
        clientRegistrations.add(clientRegistration());
        return new InMemoryClientRegistrationRepository(clientRegistrations);
    }

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    private ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("client")
                .clientId("client")
                .clientSecret("secret")
                .clientName("Custom")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope(OidcScopes.OPENID)
                .tokenUri("http://127.0.0.1:8080/oauth2/token")
                .authorizationUri("http://127.0.0.1:8080/oauth2/authorize")
                .jwkSetUri("http://127.0.0.1:8080/oauth2/jwks")
                .userInfoUri("http://127.0.0.1:8080/userinfo")
                .issuerUri("http://127.0.0.1:8080")
                .redirectUri("http://localhost:7070/login/oauth2/code/my_authorization_server")
//                .providerConfigurationMetadata(Map.of("provider", "my_authorization_server"))
                .build();
    }
}
