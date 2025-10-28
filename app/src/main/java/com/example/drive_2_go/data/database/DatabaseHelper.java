package com.example.drive_2_go.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "location_voitures.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table utilisateurs
        String createUsers = "CREATE TABLE utilisateurs (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, " +
                "email TEXT UNIQUE, " +
                "telephone TEXT, " +
                "mot_de_passe TEXT, " +
                "role TEXT)";
        db.execSQL(createUsers);

        // Table voitures
        String createCars = "CREATE TABLE voitures (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "marque TEXT, " +
                "modele TEXT, " +
                "type TEXT, " +
                "couleur TEXT, " +
                "prix_jour REAL, " +
                "disponibilite INTEGER, " +
                "photo TEXT)";
        db.execSQL(createCars);

        // Table reservations
        String createReservations = "CREATE TABLE reservations (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "voiture_id INTEGER, " +
                "utilisateur_id INTEGER, " +
                "date_debut TEXT, " +
                "date_fin TEXT, " +
                "prix_total REAL, " +
                "statut TEXT, " +
                "FOREIGN KEY(voiture_id) REFERENCES voitures(id), " +
                "FOREIGN KEY(utilisateur_id) REFERENCES utilisateurs(id))";
        db.execSQL(createReservations);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprime les anciennes tables
        db.execSQL("DROP TABLE IF EXISTS reservations");
        db.execSQL("DROP TABLE IF EXISTS voitures");
        db.execSQL("DROP TABLE IF EXISTS utilisateurs");
        onCreate(db);
    }
}

