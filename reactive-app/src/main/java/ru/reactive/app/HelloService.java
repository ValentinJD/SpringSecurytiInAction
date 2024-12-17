package ru.reactive.app;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class HelloService {


    @PreAuthorize("hasAuthority('write')")
    public Mono<String> getName() {
        return Mono.just("Fantastico");
    }

}
