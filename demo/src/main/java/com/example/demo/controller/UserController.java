package com.example.demo.controller;


import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    // Updated Register API
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok(service.register(user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserEntity user) {
        UserEntity foundUser = service.login(user.getEmail(), user.getPassword());

        if (foundUser != null) {
            return "Login Successful! Welcome " + foundUser.getUsername();
        } else {
            return "Invalid Email or Password";
        }
    }
}


