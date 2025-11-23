package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.service.EwasteRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final EwasteRequestService service;

    public UserController(EwasteRequestService service) { this.service = service; }

    @PostMapping("/create")
    public EwasteRequest create(@RequestBody EwasteRequest req) {
        return service.create(req);
    }
}

