package ru.reactive.app;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication auth) {
        return "Hello, " + auth.getName() + "!";
    }

    @GetMapping("/hello-reactive")
    public Mono<String> helloR() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(auth -> "Hello " + auth.getName());
    }

    @PostMapping("/hello-reactive")
    public Mono<String> helloRPost() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(auth -> "Hello " + auth.getName());
    }

}
