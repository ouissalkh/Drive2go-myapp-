package com.example.drive_2_go.data.model;

import java.io.Serializable;

// Car.java
public class Car implements Serializable {
    private String name;
    private String licensePlate;
    private String price;
    private int imageResId; // Resource ID pour l'image (R.drawable.img_renault_captur)
    private String fuelType;
    private String maxKm;
    private int baggageCount;
    private boolean hasAC; // Climatisation
    private String gearType; // M ou A (Manuelle ou Automatique)
    private int doorCount;
    private int peopleCount;
    private boolean isChecked; // Pour l'icône de la coche (ic_check)

    // Constructeur complet
    public Car(String name, String licensePlate, String price, int imageResId,
               String fuelType, String maxKm, int baggageCount, boolean hasAC,
               String gearType, int doorCount, int peopleCount, boolean isChecked) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.price = price;
        this.imageResId = imageResId;
        this.fuelType = fuelType;
        this.maxKm = maxKm;
        this.baggageCount = baggageCount;
        this.hasAC = hasAC;
        this.gearType = gearType;
        this.doorCount = doorCount;
        this.peopleCount = peopleCount;
        this.isChecked = isChecked;
    }

    // Getters pour tous les champs
    public String getName() { return name; }
    public String getLicensePlate() { return licensePlate; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getFuelType() { return fuelType; }
    public String getMaxKm() { return maxKm; }
    public int getBaggageCount() { return baggageCount; }
    public boolean isHasAC() { return hasAC; }
    public String getGearType() { return gearType; }
    public int getDoorCount() { return doorCount; }
    public int getPeopleCount() { return peopleCount; }
    public boolean isChecked() { return isChecked; }
}
