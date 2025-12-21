package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.security.JwtService;
import com.sample.Smart.E_waste.service.EwasteRequestService;
import com.sample.Smart.E_waste.service.FileStorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final EwasteRequestService service;
    private final JwtService jwt;

    public UserController(EwasteRequestService service, JwtService jwt) {
        this.service = service;
        this.jwt = jwt;
    }

    @PostMapping("/create")
    public EwasteRequest createRequest(
            @RequestBody EwasteRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwt.subject(token);
        return service.createRequest(request, email);
    }
}
