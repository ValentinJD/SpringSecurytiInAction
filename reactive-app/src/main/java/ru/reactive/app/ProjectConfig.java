package ru.reactive.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@ComponentScan(basePackages = {"ru.reactive.app"})
@EnableWebSecurity
@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var u = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();
        var u2 = User.withUsername("bill")
                .password("12345")
                .roles("REGULAR_USER")
                .build();
        var uds = new InMemoryUserDetailsManager(u, u2);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> c.anyRequest()
                .authenticated());
        return http.build();
    }

//    public Mono<AuthorizationDecision> getAuthorizationDecisionMono(Mono<Authentication> a, AuthorizationContext c) {
//        String path = getRequestPath(c);
//        boolean restrictedTime = LocalTime.now().isAfter(LocalTime.NOON);
//        if (path.equals("/hello")) {
//            return a.map(isAdmin())
//                    .map(auth -> auth && !restrictedTime)
//                    .map(AuthorizationDecision::new);
//        }
//        return Mono.just(new AuthorizationDecision(false));
//    }
//
//    public String getRequestPath(AuthorizationContext c) {
//        return c.getExchange()
//                .getRequest()
//                .getPath()
//                .toString();
//    }
//
//    private Function<Authentication, Boolean> isAdmin() {
//        return p ->
//                p.getAuthorities().stream()
//                .anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
//    }
}
