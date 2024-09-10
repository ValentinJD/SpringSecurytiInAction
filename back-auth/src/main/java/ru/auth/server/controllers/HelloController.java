package ru.auth.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
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
                "encode plaintText: 12345 is:  " + NoOpPasswordEncoder.getInstance().encode("{noop}12345");
    }

    @GetMapping("/generateKey")
    public String generateKey() {
        BytesKeyGenerator keyGenerator1 = KeyGenerators.secureRandom();
        byte[] key = keyGenerator1.generateKey();
        int keyLength = keyGenerator1.getKeyLength();
        BytesKeyGenerator keyGenerator2 = KeyGenerators.secureRandom(16);
        BytesKeyGenerator keyGenerator = KeyGenerators.shared(16);
        byte[] key1 = keyGenerator.generateKey();
        byte[] key2 = keyGenerator.generateKey();
        return "key1: " + new String(key1) + "\n" +
                "key2: " + new String(key2) + "\n";
    }

    @GetMapping("/encrypt")
    public String encrypt() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";
        BytesEncryptor e = Encryptors.standard(password, salt);
        byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte[] decrypted = e.decrypt(encrypted);
        return "encrypted: " + new String(encrypted) + "\n" +
                "decrypted: " + new String(decrypted) + "\n";
    }

    @GetMapping("/validate-header-filter")
    public String validateHeaderFilter() {
        return "header Request-Id: is present";
    }
}
