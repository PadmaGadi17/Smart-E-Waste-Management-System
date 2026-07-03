package com.sample.Smart.E_waste.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_status_history")
public class RequestStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private EwasteRequest request;

    private String oldStatus;
    private String newStatus;
    private String note;
    private LocalDateTime changedAt;

    @PrePersist
    public void prePersist() {
        this.changedAt = LocalDateTime.now(); // ✅ fixed
    }

    public RequestStatusHistory() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public EwasteRequest getRequest() { return request; }
    public void setRequest(EwasteRequest r) { this.request = r; }
    public String getOldStatus() { return oldStatus; }
    public void setOldStatus(String s) { this.oldStatus = s; }
    public String getNewStatus() { return newStatus; }
    public void setNewStatus(String s) { this.newStatus = s; }
    public String getNote() { return note; }
    public void setNote(String n) { this.note = n; }
    public LocalDateTime getChangedAt() { return changedAt; }
    public void setChangedAt(LocalDateTime t) { this.changedAt = t; }
}