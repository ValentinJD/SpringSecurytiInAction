package ru.auth.server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.auth.server.service.NameService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {

    private final NameService nameService;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, " + nameService.getName();
    }


}
