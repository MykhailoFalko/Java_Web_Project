package com.example.FinalProject.db.entity;

import java.io.Serializable;

public class Procedure implements Serializable {
    private int id;
    private String name;
    private double price;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Procedure " + "id=" + id + ", name=" + name  + ", price=" + price;
    }
}
