package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.security.JwtService;
import com.sample.Smart.E_waste.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService users;
    private final JwtService jwt;

    public AuthController(UserService users, JwtService jwt) {
        this.users = users;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        String token = users.register(user);
        return Map.of(
                "token", token,
                "role", user.getRole()
        );
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestParam("identifier") String identifier,
            @RequestParam("password") String password
    ) {
        User u = users.byEmail(identifier)
                .orElseGet(() ->
                        users.byPhone(identifier)
                                .orElseThrow(() -> new RuntimeException("User not found"))
                );

        if (!users.matches(password, u.getPassword())) {
            throw new RuntimeException("Bad credentials");
        }

        return Map.of(
                "token", jwt.generate(u.getEmail(), u.getRole()),
                "role", u.getRole()
        );
    }
}
