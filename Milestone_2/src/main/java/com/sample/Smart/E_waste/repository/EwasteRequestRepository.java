// repository/EwasteRequestRepository.java
package com.sample.Smart.E_waste.repository;

import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EwasteRequestRepository extends JpaRepository<EwasteRequest, Long> {
    List<EwasteRequest> findByUser(User user);
    long countByStatus(String status);
    long countByDeviceType(String deviceType);
}





