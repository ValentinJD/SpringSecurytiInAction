package ru.auth.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User  {
    @Id
    private Long id;
    private String username;
    private String password;
    private String authority;

}
