package ru.client.server;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeController {
//    @GetMapping("/")
//    public Resource home() {
//        return new ClassPathResource("templates/index.html");
//    }

    @GetMapping("/")
    public Resource home(OAuth2AuthenticationToken authentication) {
        OAuth2User principal = authentication.getPrincipal();
// do something with the authentication
        return new ClassPathResource("templates/index.html");
    }
}
