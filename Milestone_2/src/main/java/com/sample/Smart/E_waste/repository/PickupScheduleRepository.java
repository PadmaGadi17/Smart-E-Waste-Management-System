package com.sample.Smart.E_waste.repository;

import com.sample.Smart.E_waste.entity.PickupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PickupScheduleRepository extends JpaRepository<PickupSchedule, Long> {
    Optional<PickupSchedule> findByRequest_Id(Long requestId);
}

