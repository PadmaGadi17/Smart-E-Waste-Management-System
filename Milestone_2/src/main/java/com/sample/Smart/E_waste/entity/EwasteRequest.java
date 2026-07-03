package com.sample.Smart.E_waste.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ewaste_requests")
public class EwasteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String deviceType;
    private String pickupAddress;

    @Column(length = 500)
    private String description;

    private String status; // PENDING/ASSIGNED/SCHEDULED/PICKED/COMPLETED/REJECTED

    private String imagePath;
    private LocalDate preferredDate;
    private String preferredSlot;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); // ✅ set at actual save time
    }

    public EwasteRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String v) { this.customerName = v; }
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String v) { this.deviceType = v; }
    public String getPickupAddress() { return pickupAddress; }
    public void setPickupAddress(String v) { this.pickupAddress = v; }
    public String getDescription() { return description; }
    public void setDescription(String v) { this.description = v; }
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String v) { this.imagePath = v; }
    public LocalDate getPreferredDate() { return preferredDate; }
    public void setPreferredDate(LocalDate v) { this.preferredDate = v; }
    public String getPreferredSlot() { return preferredSlot; }
    public void setPreferredSlot(String v) { this.preferredSlot = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
    public User getUser() { return user; }
    public void setUser(User v) { this.user = v; }
}
