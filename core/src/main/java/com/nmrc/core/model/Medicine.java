package com.nmrc.core.model;

public class Medicine {

    private String name;
    private float priceU;

    public Medicine(String name,
                    float priceU) {
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

    public void setPriceU(float priceU) {
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
