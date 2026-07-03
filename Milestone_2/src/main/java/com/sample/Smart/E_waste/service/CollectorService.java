package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.*;
import com.sample.Smart.E_waste.repository.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class CollectorService {

    private final CollectorAssignmentRepository assignRepo;
    private final UserRepository userRepo;
    private final EwasteRequestRepository reqRepo;
    private final RequestStatusHistoryRepository historyRepo;
    private final EmailService emailService;

    public CollectorService(CollectorAssignmentRepository assignRepo,
                            UserRepository userRepo,
                            EwasteRequestRepository reqRepo,
                            RequestStatusHistoryRepository historyRepo,
                            EmailService emailService) {
        this.assignRepo = assignRepo;
        this.userRepo = userRepo;
        this.reqRepo = reqRepo;
        this.historyRepo = historyRepo;
        this.emailService = emailService;
    }

    public List<CollectorAssignment> myAssignments(String email) {
        User me = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return assignRepo.findByCollector_Id(me.getId());
    }

    @Transactional
    public EwasteRequest updateStatus(Long requestId, String newStatus) {
        EwasteRequest req = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found: " + requestId));

        String oldStatus = req.getStatus();
        req.setStatus(newStatus);
        EwasteRequest saved = reqRepo.save(req);

        // ✅ save history
        RequestStatusHistory h = new RequestStatusHistory();
        h.setRequest(saved);
        h.setOldStatus(oldStatus);
        h.setNewStatus(newStatus);
        h.setNote("Updated by collector");
        historyRepo.save(h);

        // ✅ notify user
        emailService.send(saved.getUser().getEmail(),
                "Pickup Status Updated",
                "Your request #" + saved.getId() + " is now: " + newStatus);

        return saved;
    }
}
