package com.sample.Smart.E_waste.service;

import com.sample.Smart.E_waste.entity.PickupSchedule;
import com.sample.Smart.E_waste.repository.EwasteRequestRepository;
import com.sample.Smart.E_waste.repository.PickupScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PickupService {

    private final PickupScheduleRepository repo;
    private final EwasteRequestRepository reqRepo;

    public PickupService(PickupScheduleRepository repo,
                         EwasteRequestRepository reqRepo) {
        this.repo = repo;
        this.reqRepo = reqRepo;
    }

    public PickupSchedule schedule(Long requestId, LocalDate date,
                                   LocalTime from, LocalTime to, String remarks) {
        var req = reqRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found: " + requestId));

        var sch = repo.findByRequest_Id(requestId).orElse(new PickupSchedule());
        sch.setRequest(req);
        sch.setDate(date);
        sch.setFromTime(from);
        sch.setToTime(to);
        sch.setRemarks(remarks);

        // ✅ update request status
        req.setStatus("SCHEDULED");
        reqRepo.save(req);

        return repo.save(sch);
    }

    public PickupSchedule get(Long requestId) {
        return repo.findByRequest_Id(requestId).orElse(null);
    }
}
