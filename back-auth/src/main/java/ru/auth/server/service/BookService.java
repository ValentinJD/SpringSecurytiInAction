package ru.auth.server.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.auth.server.entity.Employee;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private Map<String, Employee> records =
            Map.of("mary",
                    new Employee("Emma Thompson",
                            List.of("Karamazov Brothers"),
                            List.of("accountant", "reader")),
                    "jane",
                    new Employee("Natalie Parker",
                            List.of("Beautiful Paris"),
                            List.of("researcher"))
            );

    @PostAuthorize("returnObject.roles.contains('reader')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
