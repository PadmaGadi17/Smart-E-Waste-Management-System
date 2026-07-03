package com.sample.Smart.E_waste.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "collector_assignments")
public class CollectorAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private EwasteRequest request;

    @ManyToOne(optional = false)
    private User collector;

    private LocalDateTime assignedAt;
    private String note;

    @PrePersist
    public void prePersist() {
        this.assignedAt = LocalDateTime.now(); // ✅ fixed
    }

    public CollectorAssignment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public EwasteRequest getRequest() { return request; }
    public void setRequest(EwasteRequest r) { this.request = r; }
    public User getCollector() { return collector; }
    public void setCollector(User c) { this.collector = c; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime t) { this.assignedAt = t; }
    public String getNote() { return note; }
    public void setNote(String n) { this.note = n; }
}



