package ru.auth.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import ru.auth.server.repository.CustomCsrfTokenRepository;

@EnableAsync
@RequiredArgsConstructor
@Configuration
public class ProjectConfig {

    private final AuthenticationProvider authenticationProvider;
    private final CustomCsrfTokenRepository customTokenRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider);

//        Or you can similarly use a regex matcher:

//        http.httpBasic(Customizer.withDefaults());
        http.csrf(c -> {
            c.csrfTokenRepository(customTokenRepository);
            c.csrfTokenRequestHandler(
                    new CsrfTokenRequestAttributeHandler()
            );
            HandlerMappingIntrospector i = new HandlerMappingIntrospector();
//            MvcRequestMatcher r = new MvcRequestMatcher(i, "/ciao");
//            c.ignoringRequestMatchers(r);
            String pattern = "\\/console\\/\\w+.\\w+\\D\\D+\\w+";
            String httpMethod = HttpMethod.POST.name();
            RegexRequestMatcher r2 = new RegexRequestMatcher(pattern, httpMethod);
            c.ignoringRequestMatchers(r2);
        });
//        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(
                c -> c.requestMatchers("/console/**").permitAll()
                        .anyRequest().permitAll());
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
