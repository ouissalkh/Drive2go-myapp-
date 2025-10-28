package com.example.drive_2_go.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.drive_2_go.data.database.DatabaseHelper;
import com.example.drive_2_go.data.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private DatabaseHelper dbHelper;

    public CarRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Ajouter une voiture
    public long insertCar(Car car) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("marque", car.getMarque());
        values.put("modele", car.getModele());
        values.put("type", car.getType());
        values.put("couleur", car.getCouleur());
        values.put("prix_jour", car.getPrixJour());
        values.put("disponibilite", car.getDisponibilite());
        values.put("photo", car.getPhoto());
        long id = db.insert("voitures", null, values);
        db.close();
        return id;
    }

    // Récupérer toutes les voitures
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM voitures", null);
        if(cursor.moveToFirst()){
            do {
                Car car = new Car();
                car.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                car.setMarque(cursor.getString(cursor.getColumnIndexOrThrow("marque")));
                car.setModele(cursor.getString(cursor.getColumnIndexOrThrow("modele")));
                car.setType(cursor.getString(cursor.getColumnIndexOrThrow("type")));
                car.setCouleur(cursor.getString(cursor.getColumnIndexOrThrow("couleur")));
                car.setPrixJour(cursor.getDouble(cursor.getColumnIndexOrThrow("prix_jour")));
                car.setDisponibilite(cursor.getInt(cursor.getColumnIndexOrThrow("disponibilite")));
                car.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow("photo")));
                cars.add(car);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cars;
    }
}
