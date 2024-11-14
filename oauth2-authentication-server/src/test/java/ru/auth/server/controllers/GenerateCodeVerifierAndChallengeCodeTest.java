package ru.auth.server.controllers;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

class GenerateCodeVerifierAndChallengeCodeTest {

    @Test
    void test() {
        SecureRandom secureRandom = new SecureRandom();
        byte [] code = new byte[32];
        secureRandom.nextBytes(code);
        String codeVerifier = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(code);

        System.out.println("codeVerifier: " + codeVerifier);

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte [] digested = messageDigest.digest(codeVerifier.getBytes());
        String codeChallenge = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(digested);

        System.out.println("codeChallenge: " + codeChallenge);
    }

}