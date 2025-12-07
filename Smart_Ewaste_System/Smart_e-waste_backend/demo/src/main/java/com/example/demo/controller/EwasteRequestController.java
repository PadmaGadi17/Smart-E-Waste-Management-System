package com.example.demo.controller;

import com.example.demo.entity.EwasteRequest;
import com.example.demo.service.EwasteRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*")
public class EwasteRequestController {

    private final EwasteRequestService requestService;

    public EwasteRequestController(EwasteRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createRequest(@PathVariable Long userId,
                                           @RequestBody EwasteRequest request) {
        Optional<EwasteRequest> savedOpt = requestService.createRequest(userId, request);
        if (savedOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(savedOpt.get());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EwasteRequest>> getUserRequests(@PathVariable Long userId) {
        List<EwasteRequest> list = requestService.getRequestsForUser(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping
    public ResponseEntity<List<EwasteRequest>> getAllRequests() {
        List<EwasteRequest> list = requestService.getAllRequests();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRequest(@PathVariable Long id,
                                           @RequestBody EwasteRequest updated) {
        Optional<EwasteRequest> updatedOpt = requestService.updateRequest(id, updated);
        if (updatedOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedOpt.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long id) {
        boolean deleted = requestService.deleteRequest(id);
        if (deleted) {
            return ResponseEntity.ok("Request deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
