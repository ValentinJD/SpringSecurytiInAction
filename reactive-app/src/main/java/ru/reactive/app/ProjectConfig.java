package ru.reactive.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.function.Function;

@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
//@ComponentScan(basePackages = {"ru.reactive.app"})
//@EnableWebSecurity
@EnableWebFluxSecurity
@Configuration
@EnableReactiveMethodSecurity
public class ProjectConfig {

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        var u = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .authorities("ROLE_ADMIN")
                .build();
        var u2 = User.withUsername("bill")
                .password("12345")
                .roles("REGULAR_USER")
                .build();
        return new MapReactiveUserDetailsService(u, u2);
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
                        .authenticated()
        );
        return http.build();
    }

    public Mono<AuthorizationDecision> getAuthorizationDecisionMono(Mono<Authentication> a, AuthorizationContext c) {
        String path = getRequestPath(c);
        boolean restrictedTime = LocalTime.now().isAfter(LocalTime.NOON);
        if (path.startsWith("/hello")) {
            return a
                    .map(isAdmin())
                    .map(auth -> auth
//                            && !restrictedTime
                    )
                    .map(AuthorizationDecision::new);
        }
        return Mono.just(new AuthorizationDecision(true));
    }

    public String getRequestPath(AuthorizationContext c) {
        return c.getExchange()
                .getRequest()
                .getPath()
                .toString();
    }

    private Function<Authentication, Boolean> isAdmin() {
        return p -> true;
//                p.getAuthorities().stream()
//                .anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
    }
}
