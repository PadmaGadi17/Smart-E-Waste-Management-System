package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ GET Profile
    @GetMapping("/me")
    public User getProfile(@RequestParam String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✅ PUT Update Profile
    @PutMapping("/update")
    public String updateProfile(@RequestParam String email, @RequestBody User updatedData) {
        Optional<User> existingOpt = userRepository.findByEmail(email);

        if (existingOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User existing = existingOpt.get();

        // update only editable fields
        if (updatedData.getName() != null) existing.setName(updatedData.getName());
        if (updatedData.getPhone() != null) existing.setPhone(updatedData.getPhone());

        userRepository.save(existing);
        return "Profile updated successfully";
    }
}
