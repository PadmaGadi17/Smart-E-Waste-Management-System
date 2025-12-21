package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.repository.EwasteRequestRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AnalyticsService {

    private final EwasteRequestRepository repo;

    public AnalyticsService(EwasteRequestRepository repo) {
        this.repo = repo;
    }

    public Map<String, Object> getAdminAnalytics() {
        return Map.of(
                "totalRequests", repo.count(),
                "pending", repo.countByStatus("PENDING"),
                "assigned", repo.countByStatus("ASSIGNED"),
                "picked", repo.countByStatus("PICKED"),
                "completed", repo.countByStatus("COMPLETED")
        );
    }
}


