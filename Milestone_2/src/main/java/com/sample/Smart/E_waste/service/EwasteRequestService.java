package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.*;
import com.sample.Smart.E_waste.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EwasteRequestService {

    private final EwasteRequestRepository reqRepo;
    private final UserRepository userRepo;
    private final RequestStatusHistoryRepository historyRepo;
    private final CollectorAssignmentRepository assignRepo;
    private final EmailService emailService;

    public EwasteRequestService(EwasteRequestRepository reqRepo,
                                UserRepository userRepo,
                                RequestStatusHistoryRepository historyRepo,
                                CollectorAssignmentRepository assignRepo,
                                EmailService emailService) {
        this.reqRepo = reqRepo;
        this.userRepo = userRepo;
        this.historyRepo = historyRepo;
        this.assignRepo = assignRepo;
        this.emailService = emailService;
    }

    @Transactional
    public EwasteRequest createRequest(EwasteRequest request, String userEmail) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + userEmail));

        request.setUser(user);
        request.setStatus("PENDING");
        EwasteRequest saved = reqRepo.save(request);

        saveHistory(saved, "N/A", "PENDING", "Request created");

        emailService.send(user.getEmail(),
                "E-Waste Request Created",
                "Hi " + user.getName() + ",\nYour request #" + saved.getId() + " has been created.\nStatus: PENDING");

        return saved;
    }

    public List<EwasteRequest> getAllRequests() {
        return reqRepo.findAll();
    }

    @Transactional
    public EwasteRequest updateStatus(Long requestId, String newStatus, String note) {
        EwasteRequest req = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        String oldStatus = req.getStatus(); // ✅ capture before changing
        req.setStatus(newStatus);
        EwasteRequest saved = reqRepo.save(req);

        saveHistory(saved, oldStatus, newStatus, note);

        emailService.send(saved.getUser().getEmail(),
                "Status Updated",
                "Request #" + saved.getId() + " changed from " + oldStatus + " to " + newStatus);

        return saved;
    }

    @Transactional
    public CollectorAssignment assignCollector(Long requestId, String collectorEmail, String note) {
        EwasteRequest req = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        if (assignRepo.findByRequest_Id(requestId).isPresent()) {
            throw new RuntimeException("This request is already assigned to a collector");
        }

        User collector = userRepo.findByEmail(collectorEmail)
                .orElseThrow(() -> new RuntimeException("Collector not found"));

        // ✅ role guard
        if (!"ROLE_COLLECTOR".equals(collector.getRole()))
            throw new RuntimeException("User is not a collector");

        String oldStatus = req.getStatus(); // ✅ capture real old status

        CollectorAssignment assignment = new CollectorAssignment();
        assignment.setRequest(req);
        assignment.setCollector(collector);
        assignment.setNote(note);
        CollectorAssignment saved = assignRepo.save(assignment);

        req.setStatus("ASSIGNED");
        reqRepo.save(req);

        saveHistory(req, oldStatus, "ASSIGNED", "Assigned to collector: " + collectorEmail);

        emailService.send(collector.getEmail(), "New Pickup Assigned",
                "Address: " + req.getPickupAddress() + "\nDevice: " + req.getDeviceType());
        emailService.send(req.getUser().getEmail(), "Collector Assigned",
                "A collector has been assigned to your request #" + req.getId());

        return saved;
    }

    public List<RequestStatusHistory> history(Long requestId) {
        return historyRepo.findByRequest_IdOrderByChangedAtAsc(requestId);
    }
    public EwasteRequest updateImage(Long requestId, String imagePath) {
        EwasteRequest req = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        req.setImagePath(imagePath);
        return reqRepo.save(req);
    }
    // ✅ reusable helper
    private void saveHistory(EwasteRequest req, String oldStatus,
                             String newStatus, String note) {
        RequestStatusHistory h = new RequestStatusHistory();
        h.setRequest(req);
        h.setOldStatus(oldStatus);
        h.setNewStatus(newStatus);
        h.setNote(note);
        historyRepo.save(h);
    }
    public List<EwasteRequest> getMyRequests(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return reqRepo.findByUser(user);
    }
}





