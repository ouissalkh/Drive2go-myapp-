package com.example.drive_2_go.data.model;

import java.io.Serializable;

// Car.java
public class Car implements Serializable {
    private int id;
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
    private boolean isFavorite = false;

    // Constructeur complet
    public Car(int id, String name, String licensePlate, String price, int imageResId,
               String fuelType, String maxKm, int baggageCount, boolean hasAC,
               String gearType, int doorCount, int peopleCount, boolean isChecked, boolean isFavorite) {
        this.id =id;
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
        this.isFavorite = isFavorite;
    }


    // Getters pour tous les champs
    public int getId() { return id; }
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
    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
