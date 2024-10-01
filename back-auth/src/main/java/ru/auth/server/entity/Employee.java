package ru.auth.server.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Employee {
    private final String name;
    private final List<String> books;
    private final List<String> roles;

}
