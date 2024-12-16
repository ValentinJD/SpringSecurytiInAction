package ru.reactive.app;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String hello(Authentication auth) {
        return "Hello, " + auth.getName() + "!";
    }

    @GetMapping("/ciao")
    public String ciao() {
        return "Hello!";
    }
}
