package com.example.lenovo_pc.oncetienda;

/**
 * Created by lenovo-pc on 23/07/2018.
 */

public class Product {
    int id;
    int price;
    String name;
    String feature;

    public Product(int price, String name, String feature) {
        this.price = price;
        this.name = name;
        this.feature = feature;
    }

    public Product() {
    }

    public Product(int id, int price, String name, String feature) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.feature = feature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
