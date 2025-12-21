package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.CollectorAssignment;
import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.service.CollectorService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collector")
public class CollectorController {

    private final CollectorService service;

    public CollectorController(CollectorService service) {
        this.service = service;
    }

    // 📌 Collector views assigned pickups
    @GetMapping("/assigned")
    public List<CollectorAssignment> my(Authentication auth) {
        return service.myAssignments(auth.getName());
    }

    // 📌 Collector updates pickup status (PICKED / COMPLETED)
    @PutMapping("/updateStatus/{requestId}")
    public EwasteRequest update(
            @PathVariable("requestId") Long requestId,
            @RequestParam("status") String status
    ) {
        return service.updateStatus(requestId, status);
    }
}



