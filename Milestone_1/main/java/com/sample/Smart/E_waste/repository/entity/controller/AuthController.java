package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService users;

    public AuthController(UserService users) { this.users = users; }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User u) {
        String token = users.register(u);
        return Map.of("token", token, "role", u.getRole());
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String email, @RequestParam String password) {
        String token = users.login(email, password);
        return Map.of("token", token);
    }
}
