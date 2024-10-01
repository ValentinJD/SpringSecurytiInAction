package ru.auth.server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.auth.server.service.NameService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {

    private final NameService nameService;

    @GetMapping("/secret/names/{name}")
    public List<String> getHello(@PathVariable("name") String name) {
        return nameService.getSecretNames(name);
    }


}
