package com.nmrc.datastructure.model;

public class Appointment {

    private Patient patient;
    private AppointmentDetail detail;

    public Appointment(Patient patient,
                       AppointmentDetail detail) {
        this.patient = patient;
        this.detail = detail;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AppointmentDetail getDetail() {
        return detail;
    }

    public void setDetail(AppointmentDetail detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient +
                ", detail=" + detail +
                '}';
    }
}
