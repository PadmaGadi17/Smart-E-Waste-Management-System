package com.sample.Smart.E_waste.repository;

import com.sample.Smart.E_waste.entity.EwasteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EwasteRequestRepository extends JpaRepository<EwasteRequest, Long> {
    List<EwasteRequest> findByUser_Id(Long userId);
}



