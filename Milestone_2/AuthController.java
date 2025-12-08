package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.service.UserService;
import com.sample.Smart.E_waste.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService users;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserService users, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.users = users; this.passwordEncoder = passwordEncoder; this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public Map<String,String> register(@RequestBody User u){
        String token = users.register(u);
        var saved = users.findByEmail(u.getEmail())
                .orElseThrow(() -> new RuntimeException("Registration failed"));
        return Map.of("token", token, "role", saved.getRole());
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestParam String identifier, @RequestParam String password){
        var userOpt = users.findByEmail(identifier);
        if (userOpt.isEmpty()) userOpt = users.findByPhone(identifier);
        var user = userOpt.orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Bad credentials");

        String token = jwtService.generateToken(user.getEmail(), user.getRole());
        return Map.of("token", token, "role", user.getRole());
    }
}


