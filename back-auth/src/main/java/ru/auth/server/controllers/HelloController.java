package ru.auth.server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {

    @PostMapping("/hello")
    public String getHello() {
        return "Post Hello with CSRF!";
    }

    @PostMapping("/ciao")
    public String postHello() {
        return "Post ciao! without CSRF";
    }

}
