package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService email;
    public EmailController(EmailService email){ this.email=email; }

    @PostMapping("/test")
    public Map<String,String> send(@RequestParam String to){
        email.send(to,"Test Mail","If you see this, email works!");
        return Map.of("status","ok");
    }
}
