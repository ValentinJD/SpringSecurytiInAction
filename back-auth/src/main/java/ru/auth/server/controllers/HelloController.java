package ru.auth.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.auth.server.config.Sha512PasswordEncoder;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final SCryptPasswordEncoder scryptPasswordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Sha512PasswordEncoder sha512PasswordEncoder;

    @GetMapping("/hello")
    public String hello() {
        return "Hello!\n" +
                "encode bcrypt: 12345 is:  " + bCryptPasswordEncoder.encode("12345") + "\n" +
                "encode scrypt: 12345 is:  " + scryptPasswordEncoder.encode("{scrypt}12345") + "\n" +
                "encode sha256: 12345 is:  " + sha512PasswordEncoder.encode("{sha256}12345") + "\n" +
                "encode plaintText: 12345 is:  " + NoOpPasswordEncoder.getInstance().encode("{noop}12345") ;
    }
}
