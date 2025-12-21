package com.sample.Smart.E_waste.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="collector_assignments")
public class CollectorAssignment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private EwasteRequest request;

    @ManyToOne(optional=false)
    private User collector; // role = ROLE_COLLECTOR

    private LocalDateTime assignedAt = LocalDateTime.now();
    private String note;

    public CollectorAssignment(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public EwasteRequest getRequest(){ return request; }
    public void setRequest(EwasteRequest request){ this.request = request; }
    public User getCollector(){ return collector; }
    public void setCollector(User collector){ this.collector = collector; }
    public LocalDateTime getAssignedAt(){ return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt){ this.assignedAt = assignedAt; }
    public String getNote(){ return note; }
    public void setNote(String note){ this.note = note; }
}



