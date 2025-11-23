package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.repository.EwasteRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EwasteRequestService {

    private final EwasteRequestRepository repo;

    public EwasteRequestService(EwasteRequestRepository repo) {
        this.repo = repo;
    }

    public EwasteRequest create(EwasteRequest r) {
        if (r.getStatus() == null || r.getStatus().isBlank()) r.setStatus("PENDING");
        return repo.save(r);
    }

    public List<EwasteRequest> all() { return repo.findAll(); }

    public EwasteRequest one(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Request not found: " + id));
    }

    public EwasteRequest update(Long id, EwasteRequest in) {
        EwasteRequest ex = one(id);
        ex.setCustomerName(in.getCustomerName());
        ex.setDeviceType(in.getDeviceType());
        ex.setPickupAddress(in.getPickupAddress());
        ex.setStatus(in.getStatus());
        return repo.save(ex);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Request not found: " + id);
        repo.deleteById(id);
    }
}
