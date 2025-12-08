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

    @Column(nullable = false)          // mandatory
    private String description;

    private String status;             // PENDING / APPROVED / COMPLETED

    @ManyToOne
    @JoinColumn(name = "user_id")      // fills automatically from logged-in user
    private User user;

    public EwasteRequest() {}

    // getters & setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; } public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getDeviceType() { return deviceType; } public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public String getPickupAddress() { return pickupAddress; } public void setPickupAddress(String pickupAddress) { this.pickupAddress = pickupAddress; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
    public User getUser() { return user; } public void setUser(User user) { this.user = user; }
}



