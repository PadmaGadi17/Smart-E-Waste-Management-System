package com.sample.Smart.E_waste.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ewaste_requests")
public class EwasteRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String deviceType;
    private String pickupAddress;
    private String status; // PENDING / APPROVED / REJECTED / COMPLETED

    public EwasteRequest() {}

    public EwasteRequest(String customerName, String deviceType, String pickupAddress, String status) {
        this.customerName = customerName;
        this.deviceType = deviceType;
        this.pickupAddress = pickupAddress;
        this.status = status;
    }

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; } public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getDeviceType() { return deviceType; } public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public String getPickupAddress() { return pickupAddress; } public void setPickupAddress(String pickupAddress) { this.pickupAddress = pickupAddress; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
}

