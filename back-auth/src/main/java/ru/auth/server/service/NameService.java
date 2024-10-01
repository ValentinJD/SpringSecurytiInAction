package ru.auth.server.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private final Map<String, List<String>> secretNames =
            Map.of(
                    "natalie", List.of("Energico", "Perfecto"),
                    "mary", List.of("Fantastico"));

    @PreAuthorize("authentication.principal != #name")
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }
}
