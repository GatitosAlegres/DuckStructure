package com.nmrc.datastructure.model;

public class Doctor extends Person {


    private int yearsOfService;
    private String specialty;

    public Doctor(String firstName,
                  String lastName,
                  int age,
                  char gender,
                  int yearsOfService,
                  String specialty) {

        super(firstName, lastName, age, gender);
        this.yearsOfService = yearsOfService;
        this.specialty = specialty;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "yearsOfService=" + yearsOfService +
                ", specialty='" + specialty + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
