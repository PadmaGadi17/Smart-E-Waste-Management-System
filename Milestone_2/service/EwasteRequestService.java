package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.EwasteRequestRepository;
import com.sample.Smart.E_waste.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EwasteRequestService {
    private final EwasteRequestRepository repository;
    private final UserRepository userRepo;

    public EwasteRequestService(EwasteRequestRepository repository, UserRepository userRepo) {
        this.repository = repository; this.userRepo = userRepo;
    }

    public EwasteRequest createRequest(EwasteRequest request) {
        if (request.getDescription()==null || request.getDescription().isBlank())
            throw new RuntimeException("Description is required");

        if (request.getStatus()==null || request.getStatus().isBlank())
            request.setStatus("PENDING");

        // get current user (email stored as principal)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found: " + email));
        request.setUser(user);

        return repository.save(request);
    }

    public List<EwasteRequest> getMyRequests(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        return repository.findByUser_Id(user.getId());
    }

    public List<EwasteRequest> getAll() { return repository.findAll(); }

    public EwasteRequest updateStatus(Long id, String status) {
        var req = repository.findById(id).orElseThrow();
        req.setStatus(status);
        return repository.save(req);
    }

    public void delete(Long id){ repository.deleteById(id); }
}

