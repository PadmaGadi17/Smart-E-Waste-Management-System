package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.service.EwasteRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final EwasteRequestService service;
    public UserController(EwasteRequestService service){ this.service = service; }

    // create request (must include description)
    @PostMapping("/create")
    public EwasteRequest create(@RequestBody EwasteRequest request){
        return service.createRequest(request);
    }

    // my requests
    @GetMapping("/my")
    public List<EwasteRequest> myRequests(Authentication auth){
        return service.getMyRequests(auth.getName()); // email from token
    }
}
