package com.nmrc.core.model;

import java.util.List;

public class AppointmentDetail {

    private String date;
    private String reason;
    private List<String> symptom;

    public AppointmentDetail(String date,
                             String reason,
                             List<String> symptom) {
        this.date = date;
        this.reason = reason;
        this.symptom = symptom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getSymptom() {
        return symptom;
    }

    public void setSymptom(List<String> symptom) {
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
