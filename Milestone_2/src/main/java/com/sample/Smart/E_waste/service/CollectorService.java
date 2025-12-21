package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.CollectorAssignment;
import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.CollectorAssignmentRepository;
import com.sample.Smart.E_waste.repository.EwasteRequestRepository;
import com.sample.Smart.E_waste.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectorService {
    private final CollectorAssignmentRepository assignRepo;
    private final UserRepository userRepo;
    private final EwasteRequestRepository reqRepo;

    public CollectorService(CollectorAssignmentRepository assignRepo,
                            UserRepository userRepo,
                            EwasteRequestRepository reqRepo){
        this.assignRepo = assignRepo; this.userRepo=userRepo; this.reqRepo=reqRepo;
    }

    public List<CollectorAssignment> myAssignments(String email) {
        User me = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Use the repo method name you actually have:
        // If your repo has findByCollector_Id(...)
        return assignRepo.findByCollector_Id(me.getId());
        // Or, if you created findByCollectorId(...), then:
        // return assignRepo.findByCollectorId(me.getId());
    }

    public EwasteRequest updateStatus(Long requestId, String newStatus) {
        EwasteRequest req = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        req.setStatus(newStatus);
        return reqRepo.save(req);
    }
}
