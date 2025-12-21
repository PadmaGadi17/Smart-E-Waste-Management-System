package com.sample.Smart.E_waste.repository;

import com.sample.Smart.E_waste.entity.CollectorAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectorAssignmentRepository extends JpaRepository<CollectorAssignment, Long> {
    List<CollectorAssignment> findByCollector_Id(Long collectorId);
    Optional<CollectorAssignment> findByRequest_Id(Long requestId);
}



