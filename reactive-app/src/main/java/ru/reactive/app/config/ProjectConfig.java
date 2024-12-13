package ru.reactive.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.function.Function;

@Configuration
public class ProjectConfig {

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        var u = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();
        var u2 = User.withUsername("bill")
                .password("12345")
                .roles("REGULAR_USER")
                .build();
        var uds = new MapReactiveUserDetailsService(u, u2);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeExchange(
                c -> c.anyExchange()
                        .access(this::getAuthorizationDecisionMono)
        );
        return http.build();
    }

    private Mono<AuthorizationDecision> getAuthorizationDecisionMono(Mono<Authentication> a, AuthorizationContext c) {
        String path = getRequestPath(c);
        boolean restrictedTime = LocalTime.now().isAfter(LocalTime.NOON);
        if (path.equals("/hello")) {
            return a.map(isAdmin())
                    .map(auth -> auth && !restrictedTime)
                    .map(AuthorizationDecision::new);
        }
        return Mono.just(new AuthorizationDecision(false));
    }

    private String getRequestPath(AuthorizationContext c) {
        return c.getExchange()
                .getRequest()
                .getPath()
                .toString();
    }

    private Function<Authentication, Boolean> isAdmin() {
        return p ->
                p.getAuthorities().stream()
                .anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
    }
}
