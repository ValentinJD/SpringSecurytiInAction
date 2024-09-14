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


    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            log.info("Это новый поток поток с именем: " + Thread.currentThread().getName() +
                    " стратегия управления SecurityContext MODE_INHERITABLETHREADLOCAL");
            SecurityContext context = SecurityContextHolder.getContext();
            String name = context.getAuthentication().getName();
            log.info("Мы получаем Authentication в новом потоке context1.getAuthentication().getName(): {}", name);
            return name;
        };
        ExecutorService e = Executors.newCachedThreadPool();
        String name1;
        try {
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            name1 = "Ciao, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown();
        }
        return "Hello, " + name1 + "!";
    }

    @GetMapping("/hello")
    public String hello(Authentication a) {
        return "Hello, " + a.getName() + "!";
    }


}
