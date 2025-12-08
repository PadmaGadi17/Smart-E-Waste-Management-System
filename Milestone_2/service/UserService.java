package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.UserRepository;
import com.sample.Smart.E_waste.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public UserService(UserRepository repo, PasswordEncoder encoder, JwtService jwt) {
        this.repo = repo; this.encoder = encoder; this.jwt = jwt;
    }

    public String register(User u) {
        if (u.getEmail()==null || u.getEmail().isBlank() ||
                u.getPhone()==null || u.getPhone().isBlank()) {
            throw new RuntimeException("Email and phone are required");
        }
        if (repo.existsByEmail(u.getEmail())) throw new RuntimeException("Email already in use");
        if (repo.existsByPhone(u.getPhone())) throw new RuntimeException("Phone already in use");

        u.setPassword(encoder.encode(u.getPassword()));
        if (u.getRole()==null || u.getRole().isBlank()) u.setRole("ROLE_USER");
        repo.save(u);
        return jwt.generateToken(u.getEmail(), u.getRole());
    }

    public Optional<User> findByEmail(String email){ return repo.findByEmail(email); }
    public Optional<User> findByPhone(String phone){ return repo.findByPhone(phone); }
}

