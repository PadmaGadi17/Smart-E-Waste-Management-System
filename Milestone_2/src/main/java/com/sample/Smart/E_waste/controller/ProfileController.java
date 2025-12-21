package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final UserRepository repo;
    public ProfileController(UserRepository repo){ this.repo = repo; }

    @GetMapping("/me")
    public User me(Authentication auth){
        return repo.findByEmail(auth.getName()).orElseThrow();
    }

    @PutMapping("/update")
    public String update(Authentication auth, @RequestBody User patch){
        var u = repo.findByEmail(auth.getName()).orElseThrow();
        if(patch.getName()!=null) u.setName(patch.getName());
        if(patch.getPhone()!=null) u.setPhone(patch.getPhone());
        repo.save(u);
        return "Profile updated";
    }
}

