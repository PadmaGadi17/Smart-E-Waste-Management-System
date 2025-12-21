package com.sample.Smart.E_waste.controller;

import com.sample.Smart.E_waste.entity.PickupSchedule;
import com.sample.Smart.E_waste.service.PickupService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/pickup")
public class PickupController {
    private final PickupService service;
    public PickupController(PickupService service){ this.service = service; }

    @PostMapping("/schedule/{requestId}")
    public PickupSchedule schedule(
            @PathVariable("requestId") Long requestId,

            @RequestParam("date") String date,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam(value = "remarks", required = false) String remarks
    ) {
        return service.schedule(
                requestId,
                LocalDate.parse(date),
                LocalTime.parse(from),
                LocalTime.parse(to),
                remarks
        );
    }


    @GetMapping("/{requestId}")
    public PickupSchedule get(@PathVariable Long requestId){
        return service.get(requestId);
    }
}

