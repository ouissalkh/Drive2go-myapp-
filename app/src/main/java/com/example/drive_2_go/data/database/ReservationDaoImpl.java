package com.example.drive_2_go.data.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.drive_2_go.data.dao.ReservationDao;
import com.example.drive_2_go.data.model.Reservation;
import com.example.drive_2_go.data.model.ReservationMonthCount;

import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {

    private final AppDatabase database;

    public ReservationDaoImpl(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void insert(Reservation reservation) {
        SQLiteDatabase db = database.getWritableDatabase();
        db.execSQL(
                "INSERT INTO reservations (carId, userId, date) VALUES (?, ?, ?)",
                new Object[]{reservation.carId, reservation.userId, reservation.date}
        );
        db.close();
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM reservations", null);
        if (cursor.moveToFirst()) {
            do {
                Reservation r = new Reservation(
                        cursor.getInt(cursor.getColumnIndexOrThrow("carId")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("userId")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date"))
                );
                r.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                list.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public int getReservationsCount() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM reservations", null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }

    @Override
    public List<ReservationMonthCount> getReservationsPerMonth() {
        List<ReservationMonthCount> list = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT strftime('%m', date) AS month, COUNT(*) AS total " +
                        "FROM reservations " +
                        "WHERE strftime('%Y', date) = strftime('%Y', 'now') " +
                        "GROUP BY strftime('%m', date)",
                null
        );
        if (cursor.moveToFirst()) {
            do {
                ReservationMonthCount r = new ReservationMonthCount();
                r.month = cursor.getString(cursor.getColumnIndexOrThrow("month"));
                r.total = cursor.getInt(cursor.getColumnIndexOrThrow("total"));
                list.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
