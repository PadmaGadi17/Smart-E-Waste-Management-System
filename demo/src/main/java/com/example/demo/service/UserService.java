package com.example.demo.service;


import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // Register User
    public UserEntity register(UserEntity user) {

        // Check if email already exists
        UserEntity existingUser = repo.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Email already registered. Please login!");
        }

        return repo.save(user);
    }

    // Login User
    public UserEntity login(String email, String password) {
        UserEntity user = repo.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

