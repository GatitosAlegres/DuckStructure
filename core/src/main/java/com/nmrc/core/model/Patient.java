package com.nmrc.core.model;

public class Patient extends Person{

    private String dni;

    public Patient(
            String firstName,
            String lastName,
            int age,
            char gender,
            String dni) {
        super(firstName, lastName, age, gender);
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "dni='" + dni + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
