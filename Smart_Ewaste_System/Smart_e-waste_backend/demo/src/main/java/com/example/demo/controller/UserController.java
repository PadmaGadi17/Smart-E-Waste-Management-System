package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Name cannot be empty"));
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Email is required"));
        }

        if (!user.getEmail().contains("@")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Invalid email format"));
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Password cannot be empty"));
        }


        Optional<User> existing = userService.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Email already registered"));
        }


        User savedUser = userService.register(user);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Registration successful!",
                        "userId", savedUser.getId()
                )
        );
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()
                || request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Email and password are required"));
        }

        Optional<User> userOpt = userService.login(request.getEmail(), request.getPassword());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
   
            return ResponseEntity.ok(
                    Map.of(
                            "message", "Login successful",
                            "userId", user.getId(),
                            "name", user.getName(),
                            "email", user.getEmail(),
                            "phone", user.getPhone(),
                            "address", user.getAddress()
                    )
            );
        } else {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("error", "Invalid email or password"));
        }
    }
}
