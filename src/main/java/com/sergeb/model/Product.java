package com.sergeb.model;

public class Product {

    private int id;
    private String name;
    private String description;
    private double price;

    public Product(Integer id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "#" + id + ": " + name + " - " + description + ", price = " + price + " USD";
    }
}
