package com.nmrc.datastructure.model;

public class Medicine {

    private String name;
    private double priceU;

    public Medicine(String name,
                    double priceU) {
        this.name = name;
        this.priceU = priceU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceU() {
        return priceU;
    }

    public void setPriceU(double priceU) {
        this.priceU = priceU;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", priceU=" + priceU +
                '}';
    }
}
