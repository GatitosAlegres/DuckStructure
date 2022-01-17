package com.nmrc.datastructure.model;

public class Alumno extends Persona {

    private float promedioP;
    private int creditos;

    public Alumno(String nombre,
                  String apellidos,
                  int edad,
                  char sexo,
                  float promedioP,
                  int creditos) {
        super(nombre, apellidos, edad, sexo);
        this.promedioP = promedioP;
        this.creditos = creditos;
    }

    public float getPromedioP() {
        return promedioP;
    }

    public void setPromedioP(float promedioP) {
        this.promedioP = promedioP;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "promedioP=" + promedioP +
                ", creditos=" + creditos +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", sexo=" + sexo +
                '}';
    }
}
