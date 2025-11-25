package com.example.drive_2_go.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.drive_2_go.data.dao.CarDao;
import com.example.drive_2_go.data.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {

    private final AppDatabase database;

    public CarDaoImpl(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void insert(Car car) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("brand", car.getBrand());
        values.put("model", car.getModel());
        values.put("price", car.getPrice());
        values.put("available", car.isAvailable() ? 1 : 0);

        db.insert("car", null, values);
        db.close();
    }

    @Override
    public void update(Car car) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("brand", car.getBrand());
        values.put("model", car.getModel());
        values.put("price", car.getPrice());
        values.put("available", car.isAvailable() ? 1 : 0);

        db.update("car", values, "id=?", new String[]{String.valueOf(car.getId())});
        db.close();
    }

    @Override
    public void delete(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete("car", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public List<Car> getAll() {
        List<Car> list = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM car", null);

        if (cursor.moveToFirst()) {
            do {
                Car car = new Car(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("brand")),
                        cursor.getString(cursor.getColumnIndexOrThrow("model")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("available")) == 1
                );
                list.add(car);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }
}
