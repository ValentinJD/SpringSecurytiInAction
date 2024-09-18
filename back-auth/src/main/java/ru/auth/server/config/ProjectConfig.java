package ru.auth.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableAsync
@RequiredArgsConstructor
@Configuration
public class ProjectConfig {

    private final AuthenticationProvider authenticationProvider;
    private final CustomAuthenticationSuccessHandler
            authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler
            authenticationFailureHandler;
    private final UserDetailsManager userDetailsManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider);
        createUsers();
        http.formLogin(c -> c.successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler));

        http.httpBasic(Customizer.withDefaults());

        // H2 Config
        http.authorizeHttpRequests(
                c -> c.requestMatchers("/console/**").permitAll()
                        .anyRequest().authenticated()
        );
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    public void createUsers() {
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("john")
//                        .password("12345")
//                        .authorities("read")
//                        .build()
//        );
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("bill")
//                        .password("12345")
//                        .authorities("write")
//                        .build()
//        );
    }

}
