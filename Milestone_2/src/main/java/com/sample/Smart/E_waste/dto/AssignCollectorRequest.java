package com.sample.Smart.E_waste.dto;

public class AssignCollectorRequest {

    private String collectorEmail;
    private String note;

    public String getCollectorEmail() {
        return collectorEmail;
    }

    public void setCollectorEmail(String collectorEmail) {
        this.collectorEmail = collectorEmail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
