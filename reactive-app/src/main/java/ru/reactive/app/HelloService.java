package ru.reactive.app;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
public class HelloService {


    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "Fantastico";
    }

}
