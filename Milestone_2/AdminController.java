package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.service.EwasteRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final EwasteRequestService service;
    public AdminController(EwasteRequestService service){ this.service = service; }

    @GetMapping("/all")
    public List<EwasteRequest> all(){ return service.getAll(); }

    @PutMapping("/updateStatus/{id}")
    public EwasteRequest updateStatus(@PathVariable Long id, @RequestParam String status){
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "Request " + id + " deleted";
    }
}

