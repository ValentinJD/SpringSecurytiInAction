package ru.auth.server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.auth.server.entity.Employee;
import ru.auth.server.service.BookService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {

    private final BookService bookService;

    @GetMapping("/book/details/{name}")
    public Employee getDetails(@PathVariable("name") String name) {
        return bookService.getBookDetails(name);
    }


}
