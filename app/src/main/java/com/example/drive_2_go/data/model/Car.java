package com.example.drive_2_go.data.model;

public class Car {
    private int id;
    private String marque;
    private String modele;
    private String type;
    private String couleur;
    private double prixJour;
    private int disponibilite;
    private String photo;

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

    public double getPrixJour() { return prixJour; }
    public void setPrixJour(double prixJour) { this.prixJour = prixJour; }

    public int getDisponibilite() { return disponibilite; }
    public void setDisponibilite(int disponibilite) { this.disponibilite = disponibilite; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
}
