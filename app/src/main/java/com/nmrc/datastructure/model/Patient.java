package com.nmrc.datastructure.model;

public class Patient extends Person{

    public Patient(String firstName,
                   String lastName,
                   int age,
                   char gender) {
        super(firstName, lastName, age, gender);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
