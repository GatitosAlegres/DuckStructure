package com.nmrc.datastructure.model;

import com.nmrc.core.linkedlist.LinkedList;

import java.time.LocalDateTime;

public class AppointmentDetail {

    private LocalDateTime date;
    private String reason;
    private LinkedList<String> symptom;

    public AppointmentDetail(LocalDateTime date,
                             String reason,
                             LinkedList<String> symptom) {
        this.date = date;
        this.reason = reason;
        this.symptom = symptom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LinkedList<String> getSymptom() {
        return symptom;
    }

    public void setSymptom(LinkedList<String> symptom) {
        this.symptom = symptom;
    }

    @Override
    public String toString() {
        return "AppointmentDetail{" +
                "date=" + date +
                ", reason='" + reason + '\'' +
                ", symptom=" + symptom +
                '}';
    }
}
