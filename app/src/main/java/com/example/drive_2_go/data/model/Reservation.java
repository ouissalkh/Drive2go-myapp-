package com.example.drive_2_go.data.model;

public class Reservation {
    public int id;
    public int carId;
    public int userId;
    public String date; // yyyy-MM-dd

    public Reservation(int carId, int userId, String date) {
        this.carId = carId;
        this.userId = userId;
        this.date = date;
    }
}
