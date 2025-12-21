package com.sample.Smart.E_waste.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ewaste_requests")
public class EwasteRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String deviceType;
    private String pickupAddress;

    @Column(length = 500)
    private String description;

    // PENDING / SCHEDULED / APPROVED / PICKED / COMPLETED / REJECTED
    private String status;

    // optional file path
    private String imagePath;

    private LocalDate preferredDate;
    private String preferredSlot; // MORNING/AFTERNOON/EVENING

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private User user;

    public EwasteRequest(){}

    // getters/setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getCustomerName(){ return customerName; }
    public void setCustomerName(String customerName){ this.customerName = customerName; }
    public String getDeviceType(){ return deviceType; }
    public void setDeviceType(String deviceType){ this.deviceType = deviceType; }
    public String getPickupAddress(){ return pickupAddress; }
    public void setPickupAddress(String pickupAddress){ this.pickupAddress = pickupAddress; }
    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description = description; }
    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }
    public String getImagePath(){ return imagePath; }
    public void setImagePath(String imagePath){ this.imagePath = imagePath; }
    public LocalDate getPreferredDate(){ return preferredDate; }
    public void setPreferredDate(LocalDate preferredDate){ this.preferredDate = preferredDate; }
    public String getPreferredSlot(){ return preferredSlot; }
    public void setPreferredSlot(String preferredSlot){ this.preferredSlot = preferredSlot; }
    public LocalDateTime getCreatedAt(){ return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }
    public User getUser(){ return user; }
    public void setUser(User user){ this.user = user; }
}
