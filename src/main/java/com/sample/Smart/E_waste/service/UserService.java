package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.UserRepository;
import com.sample.Smart.E_waste.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public UserService(UserRepository repo, PasswordEncoder encoder, JwtService jwt) {
        this.repo = repo; this.encoder = encoder; this.jwt = jwt;
    }

    public String register(User u) {
        if (repo.existsByEmail(u.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        u.setPassword(encoder.encode(u.getPassword()));
        if (u.getRole() == null || u.getRole().isBlank()) {
            u.setRole("ROLE_USER");
        }
        repo.save(u);
        return jwt.generateToken(u.getEmail(), u.getRole());
    }

    public String login(String email, String password) {
        User u = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (!encoder.matches(password, u.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwt.generateToken(u.getEmail(), u.getRole());
    }
}

