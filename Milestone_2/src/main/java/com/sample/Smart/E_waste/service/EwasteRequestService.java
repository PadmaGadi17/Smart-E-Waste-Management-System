package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.CollectorAssignment;
import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.entity.RequestStatusHistory;
import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.CollectorAssignmentRepository;
import com.sample.Smart.E_waste.repository.EwasteRequestRepository;
import com.sample.Smart.E_waste.repository.RequestStatusHistoryRepository;
import com.sample.Smart.E_waste.repository.UserRepository;
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

    public EwasteRequestService(
            EwasteRequestRepository reqRepo,
            UserRepository userRepo,
            RequestStatusHistoryRepository historyRepo,
            CollectorAssignmentRepository assignRepo,
            EmailService emailService
    ) {
        this.reqRepo = reqRepo;
        this.userRepo = userRepo;
        this.historyRepo = historyRepo;
        this.assignRepo = assignRepo;
        this.emailService = emailService;
    }

    /* =========================
       CREATE USER REQUEST
       ========================= */
    @Transactional
    public EwasteRequest createRequest(EwasteRequest request, String userEmail) {

        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + userEmail));

        request.setUser(user);
        request.setStatus("PENDING");

        EwasteRequest saved = reqRepo.save(request);

        // Status history
        RequestStatusHistory history = new RequestStatusHistory();
        history.setRequest(saved);
        history.setOldStatus("N/A");
        history.setNewStatus("PENDING");
        history.setChangedAt(LocalDateTime.now());
        history.setNote("Request created");
        historyRepo.save(history);

        // Email user
        emailService.send(
                user.getEmail(),
                "E-Waste Request Created",
                "Hi " + user.getName() + ",\n\n" +
                        "Your e-waste request (ID: " + saved.getId() + ") has been created.\n" +
                        "Status: PENDING\n\nThank you."
        );

        return saved;
    }

    /* =========================
       LIST ALL REQUESTS (ADMIN)
       ========================= */
    public List<EwasteRequest> getAllRequests() {
        return reqRepo.findAll();
    }

    /* =========================
       UPDATE STATUS
       ========================= */
    @Transactional
    public EwasteRequest updateStatus(Long requestId, String newStatus, String note) {

        EwasteRequest req = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        String oldStatus = req.getStatus();
        req.setStatus(newStatus);

        EwasteRequest saved = reqRepo.save(req);

        RequestStatusHistory history = new RequestStatusHistory();
        history.setRequest(saved);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedAt(LocalDateTime.now());
        history.setNote(note);
        historyRepo.save(history);

        // Notify user
        emailService.send(
                saved.getUser().getEmail(),
                "E-Waste Status Updated",
                "Hello " + saved.getUser().getName() + ",\n\n" +
                        "Your request #" + saved.getId() +
                        " status changed from " + oldStatus +
                        " to " + newStatus + "."
        );

        return saved;
    }

    /* =========================
       ASSIGN COLLECTOR
       ========================= */
    @Transactional
    public CollectorAssignment assignCollector(Long requestId, String collectorEmail, String note) {

        EwasteRequest req = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        User collector = userRepo.findByEmail(collectorEmail)
                .orElseThrow(() -> new RuntimeException("Collector not found"));

        CollectorAssignment assignment = new CollectorAssignment();
        assignment.setRequest(req);
        assignment.setCollector(collector);
        assignment.setNote(note);
        assignment.setAssignedAt(LocalDateTime.now());

        CollectorAssignment savedAssignment = assignRepo.save(assignment);

        req.setStatus("ASSIGNED");
        reqRepo.save(req);

        // History
        RequestStatusHistory history = new RequestStatusHistory();
        history.setRequest(req);
        history.setOldStatus("PENDING");
        history.setNewStatus("ASSIGNED");
        history.setChangedAt(LocalDateTime.now());
        history.setNote("Assigned to collector");
        historyRepo.save(history);

        // Email collector
        emailService.send(
                collector.getEmail(),
                "New Pickup Assigned",
                "Pickup Address: " + req.getPickupAddress() +
                        "\nDevice: " + req.getDeviceType()
        );

        // Email user
        emailService.send(
                req.getUser().getEmail(),
                "Collector Assigned",
                "A collector has been assigned to your request #" + req.getId()
        );

        return savedAssignment;
    }

    /* =========================
       HISTORY
       ========================= */
    public List<RequestStatusHistory> history(Long requestId) {
        return historyRepo.findByRequest_IdOrderByChangedAtAsc(requestId);
    }
}





