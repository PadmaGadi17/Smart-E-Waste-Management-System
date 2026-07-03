package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.repository.EwasteRequestRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AnalyticsService {

    private final EwasteRequestRepository repo;

    public AnalyticsService(EwasteRequestRepository repo) {
        this.repo = repo;
    }

    public Map<String, Object> getAdminAnalytics() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("totalRequests", repo.count());
        map.put("pending",       repo.countByStatus("PENDING"));
        map.put("assigned",      repo.countByStatus("ASSIGNED"));
        map.put("scheduled",     repo.countByStatus("SCHEDULED")); // ✅ added
        map.put("picked",        repo.countByStatus("PICKED"));
        map.put("completed",     repo.countByStatus("COMPLETED"));
        map.put("rejected",      repo.countByStatus("REJECTED"));  // ✅ added
        return map;
    }
}


