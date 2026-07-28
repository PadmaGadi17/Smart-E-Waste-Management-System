package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.dto.AssignCollectorRequest;
import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.entity.User;
import com.sample.Smart.E_waste.repository.UserRepository;
import com.sample.Smart.E_waste.service.AnalyticsService;
import com.sample.Smart.E_waste.service.EmailService;
import com.sample.Smart.E_waste.service.EwasteRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final EwasteRequestService service;
    private final EmailService emailService;
    private final AnalyticsService analyticsService;
    private final UserRepository userRepository; // ✅ NEW

    public AdminController(
            EwasteRequestService service,
            EmailService emailService,
            AnalyticsService analyticsService,
            UserRepository userRepository // ✅ NEW
    ) {
        this.service = service;
        this.emailService = emailService;
        this.analyticsService = analyticsService;
        this.userRepository = userRepository; // ✅ NEW
    }

    // 1️⃣ ADMIN – VIEW ALL REQUESTS
    @GetMapping("/all")
    public List<EwasteRequest> getAllRequests() {
        return service.getAllRequests();
    }

    // 2️⃣ ADMIN – ASSIGN COLLECTOR
    @PostMapping("/assign/{requestId}")
    public EwasteRequest assignCollector(
            @PathVariable("requestId") Long requestId,
            @RequestBody AssignCollectorRequest body
    ) {
        return service.assignCollector(
                requestId,
                body.getCollectorEmail(),
                body.getNote()
        ).getRequest();
    }

    // 3️⃣ ADMIN – UPDATE STATUS
    @PutMapping("/updateStatus/{requestId}")
    public EwasteRequest updateStatus(
            @PathVariable("requestId") Long requestId,
            @RequestParam("status") String status,
            @RequestParam(value = "note", required = false) String note
    ) {
        return service.updateStatus(
                requestId,
                status,
                note != null ? note : "Status updated by admin"
        );
    }

    // 4️⃣ ADMIN – ANALYTICS
    @GetMapping("/analytics")
    public Map<String, Object> analytics() {
        return analyticsService.getAdminAnalytics();
    }

    // 5️⃣ ADMIN – VIEW ALL USERS ✅ NEW
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 6️⃣ ADMIN – CHANGE USER ROLE ✅ NEW
    @PutMapping("/users/{userId}/role")
    public String changeRole(
            @PathVariable("userId") Long userId,
            @RequestParam("role") String role
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(role);
        userRepository.save(user);
        return "Role updated to " + role;
    }

    // 7️⃣ ADMIN – DELETE USER ✅ NEW
    @DeleteMapping("/users/{userId}")
    public String deleteUser(
            @PathVariable("userId") Long userId
    ) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }
    // 8️⃣ ADMIN – FILTER REQUESTS BY STATUS
    @GetMapping("/requests/filter")
    public List<EwasteRequest> filterRequests(
            @RequestParam(value = "status", required = false) String status
    ) {
        return service.getRequestsByStatus(status);
    }
}





