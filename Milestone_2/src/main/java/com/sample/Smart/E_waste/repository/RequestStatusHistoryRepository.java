package com.sample.Smart.E_waste.repository;

import com.sample.Smart.E_waste.entity.RequestStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestStatusHistoryRepository extends JpaRepository<RequestStatusHistory, Long> {
    List<RequestStatusHistory> findByRequest_IdOrderByChangedAtAsc(Long requestId);
}


