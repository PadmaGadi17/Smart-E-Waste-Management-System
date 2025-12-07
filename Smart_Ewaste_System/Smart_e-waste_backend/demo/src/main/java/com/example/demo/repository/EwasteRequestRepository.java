package com.example.demo.repository;

import com.example.demo.entity.EwasteRequest;
import com.example.demo.entity.EwasteRequest.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EwasteRequestRepository extends JpaRepository<EwasteRequest, Long> {

    List<EwasteRequest> findByUserId(Long userId);

    List<EwasteRequest> findByStatus(RequestStatus status);
}
