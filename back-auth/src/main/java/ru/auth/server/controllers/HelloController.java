package ru.auth.server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {

    @Async
    @GetMapping("/ciao")
    public void ciao() throws Exception {
        log.info("Это новый поток поток с именем: " + Thread.currentThread().getName() +
                " стратегия управления SecurityContext MODE_INHERITABLETHREADLOCAL");
        SecurityContext context1 = SecurityContextHolder.getContext();
        String name1 = context1.getAuthentication().getName();
        log.info("Мы получаем Authentication в новом потоке context1.getAuthentication().getName(): {}", name1);
    }

    @GetMapping("/hello")
    public String hello(Authentication a) {
        return "Hello, " + a.getName() + "!";
    }


}
