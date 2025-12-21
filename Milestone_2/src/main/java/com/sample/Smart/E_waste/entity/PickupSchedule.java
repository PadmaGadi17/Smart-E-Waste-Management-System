package com.sample.Smart.E_waste.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="pickup_schedule")
public class PickupSchedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional=false)
    private EwasteRequest request;

    private LocalDate date;
    private LocalTime fromTime;
    private LocalTime toTime;
    private String remarks;

    public PickupSchedule(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public EwasteRequest getRequest(){ return request; }
    public void setRequest(EwasteRequest request){ this.request = request; }
    public LocalDate getDate(){ return date; }
    public void setDate(LocalDate date){ this.date = date; }
    public LocalTime getFromTime(){ return fromTime; }
    public void setFromTime(LocalTime fromTime){ this.fromTime = fromTime; }
    public LocalTime getToTime(){ return toTime; }
    public void setToTime(LocalTime toTime){ this.toTime = toTime; }
    public String getRemarks(){ return remarks; }
    public void setRemarks(String remarks){ this.remarks = remarks; }
}

