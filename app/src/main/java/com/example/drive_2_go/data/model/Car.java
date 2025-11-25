package com.example.drive_2_go.data.model;

public class Car {
    private int id;
    private String brand;
    private String model;
    private double price;
    private boolean available;

    public Car(int id, String brand, String model, double price, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.available = available;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
