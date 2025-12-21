package com.sample.Smart.E_waste.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="request_status_history")
public class RequestStatusHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private EwasteRequest request;

    private String oldStatus;
    private String newStatus;
    private String note;
    private LocalDateTime changedAt = LocalDateTime.now();

    public RequestStatusHistory(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public EwasteRequest getRequest(){ return request; }
    public void setRequest(EwasteRequest request){ this.request = request; }
    public String getOldStatus(){ return oldStatus; }
    public void setOldStatus(String oldStatus){ this.oldStatus = oldStatus; }
    public String getNewStatus(){ return newStatus; }
    public void setNewStatus(String newStatus){ this.newStatus = newStatus; }
    public String getNote(){ return note; }
    public void setNote(String note){ this.note = note; }
    public LocalDateTime getChangedAt(){ return changedAt; }
    public void setChangedAt(LocalDateTime changedAt){ this.changedAt = changedAt; }
}



