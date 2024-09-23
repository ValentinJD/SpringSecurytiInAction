package ru.auth.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import ru.auth.server.config.filters.CsrfTokenLogger;

@EnableAsync
@RequiredArgsConstructor
@Configuration
public class ProjectConfig {

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider);
        http.httpBasic(Customizer.withDefaults());

        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeHttpRequests(
                        c -> c.anyRequest().permitAll()
                );

//        http.authorizeHttpRequests(
//                c -> c.requestMatchers("/console/**").permitAll()
//                        .requestMatchers("/product/{code:^[0-9]*$}")
//                        .permitAll()
//                        .anyRequest()
//                        .denyAll());

//        http.csrf(AbstractHttpConfigurer::disable);
        // Disables CSRF to enable a
        //call to the /a path using the
        //HTTP POST method
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
        SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();
        SecurityContextHolderFilter securityContextHolderFilter =
                new SecurityContextHolderFilter(securityContextRepository);
        securityContextHolderFilter.setSecurityContextHolderStrategy(SecurityContextHolder.getContextHolderStrategy());
        http.addFilter(securityContextHolderFilter);
        return http.build();
    }

}
