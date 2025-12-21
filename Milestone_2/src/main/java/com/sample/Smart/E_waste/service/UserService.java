package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.UserRepository;
import com.sample.Smart.E_waste.security.JwtService;
import com.sample.Smart.E_waste.util.Roles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final JwtService jwt;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo,
                       JwtService jwt,
                       PasswordEncoder encoder) {
        this.repo = repo;
        this.jwt = jwt;
        this.encoder = encoder;
    }

    public String register(User u) {

        // ✅ Duplicate checks (VERY IMPORTANT)
        if (repo.findByEmail(u.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (repo.findByPhone(u.getPhone()).isPresent()) {
            throw new RuntimeException("Phone already exists");
        }

        // ✅ Default role
        if (u.getRole() == null || u.getRole().isEmpty()) {
            u.setRole(Roles.USER);
        }

        // ✅ Encode password
        u.setPassword(encoder.encode(u.getPassword()));

        repo.save(u);

        // ✅ Return JWT
        return jwt.generate(u.getEmail(), u.getRole());
    }

    public Optional<User> byEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<User> byPhone(String phone) {
        return repo.findByPhone(phone);
    }

    public boolean matches(String raw, String hash) {
        return encoder.matches(raw, hash);
    }
}




