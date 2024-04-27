package dev.subrotokumar.authentication.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/register")
    public void register() {}

    @PostMapping("/authenticate")
    public String postMethodName(@RequestBody String entity) {
        return entity;
    }

    @PostMapping("/refresh-token")
    public String refreshToken(@RequestBody String entity) {
        return entity;
    }
}
