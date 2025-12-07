package com.example.demo.service;

import com.example.demo.entity.EwasteRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.EwasteRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EwasteRequestService {

    private final EwasteRequestRepository requestRepository;
    private final UserRepository userRepository;

    public EwasteRequestService(EwasteRequestRepository requestRepository,
                                UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Optional<EwasteRequest> createRequest(Long userId, EwasteRequest request) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        request.setUser(userOpt.get());
        request.setStatus(EwasteRequest.RequestStatus.PENDING);

        EwasteRequest saved = requestRepository.save(request);
        return Optional.of(saved);
    }

    public List<EwasteRequest> getRequestsForUser(Long userId) {
        return requestRepository.findByUserId(userId);
    }

    public List<EwasteRequest> getAllRequests() {
        return requestRepository.findAll();
    }


    public boolean deleteRequest(Long id) {
        if (requestRepository.existsById(id)) {
            requestRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Optional<EwasteRequest> updateRequest(Long id, EwasteRequest updated) {
        Optional<EwasteRequest> existingOpt = requestRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return Optional.empty();
        }

        EwasteRequest existing = existingOpt.get();

        existing.setItemType(updated.getItemType());
        existing.setBrand(updated.getBrand());
        existing.setConditionDescription(updated.getConditionDescription());
        existing.setAddress(updated.getAddress());
        existing.setPreferredPickupTime(updated.getPreferredPickupTime());

        if (updated.getStatus() != null) {
            existing.setStatus(updated.getStatus());
        }

        EwasteRequest saved = requestRepository.save(existing);
        return Optional.of(saved);
    }
}
