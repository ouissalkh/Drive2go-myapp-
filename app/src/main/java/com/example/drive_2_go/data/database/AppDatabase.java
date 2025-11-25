package com.example.drive_2_go.data.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.drive_2_go.data.dao.CarDao;
import com.example.drive_2_go.data.dao.ReservationDao;
import com.example.drive_2_go.data.dao.UserDao;
import com.example.drive_2_go.data.model.Car;
import com.example.drive_2_go.data.model.Reservation;
import com.example.drive_2_go.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {

    private static AppDatabase instance;

    private AppDatabase(Context context) {
        super(context, "drive2go.db", null, 1);
    }

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabase(context.getApplicationContext());
        }
        return instance;
    }

    public CarDao carDao() {
        return new CarDaoImpl(this);
    }

    public UserDao userDao() {
        return new UserDaoImpl(this);
    }

    public ReservationDao reservationDao() {
        return new ReservationDaoImpl(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table Car
        db.execSQL("CREATE TABLE IF NOT EXISTS car (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "brand TEXT," +
                "model TEXT," +
                "price REAL," +
                "available INTEGER)");

        // Table Users
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT)");

        // Table Reservations
        db.execSQL("CREATE TABLE IF NOT EXISTS reservations (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "carId INTEGER," +
                "userId INTEGER," +
                "date TEXT)");

        // Remplir les données initiales
        seedData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS car");
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS reservations");
        onCreate(db);
    }

    private void seedData() {
        CarDao carDao = carDao();
        UserDao userDao = userDao();
        ReservationDao reservationDao = reservationDao();

        // Vérifier si les voitures existent déjà
        if (carDao.getAll().isEmpty()) {
            carDao.insert(new Car(0, "Toyota", "Corolla", 20000, true));
            carDao.insert(new Car(0, "Honda", "Civic", 22000, true));
            carDao.insert(new Car(0, "Ford", "Focus", 18000, true));
        }

        // Vérifier si les utilisateurs existent déjà
        if (userDao.getAllUsers().isEmpty()) {
            userDao.insert(new User("Alice", "alice@mail.com"));
            userDao.insert(new User("Bob", "bob@mail.com"));
            userDao.insert(new User("Charlie", "charlie@mail.com"));
        }

        // Vérifier si les réservations existent déjà
        if (reservationDao.getAllReservations().isEmpty()) {
            // Janvier
            reservationDao.insert(new Reservation(1,1,"2025-01-05"));
            reservationDao.insert(new Reservation(2,2,"2025-01-15"));
            // Février
            reservationDao.insert(new Reservation(1,3,"2025-02-07"));
            reservationDao.insert(new Reservation(3,1,"2025-02-20"));
            // Mars
            reservationDao.insert(new Reservation(2,2,"2025-03-10"));
            reservationDao.insert(new Reservation(3,3,"2025-03-25"));
            // Avril
            reservationDao.insert(new Reservation(1,1,"2025-04-03"));
            reservationDao.insert(new Reservation(2,2,"2025-04-18"));
            // Mai
            reservationDao.insert(new Reservation(3,3,"2025-05-09"));
            reservationDao.insert(new Reservation(1,1,"2025-05-21"));
            // Juin
            reservationDao.insert(new Reservation(2,2,"2025-06-12"));
            reservationDao.insert(new Reservation(3,3,"2025-06-22"));
            // Juillet
            reservationDao.insert(new Reservation(1,1,"2025-07-05"));
            reservationDao.insert(new Reservation(2,2,"2025-07-19"));
            // Août
            reservationDao.insert(new Reservation(3,3,"2025-08-07"));
            reservationDao.insert(new Reservation(1,1,"2025-08-20"));
            // Septembre
            reservationDao.insert(new Reservation(2,2,"2025-09-03"));
            reservationDao.insert(new Reservation(3,3,"2025-09-18"));
            // Octobre
            reservationDao.insert(new Reservation(1,1,"2025-10-08"));
            reservationDao.insert(new Reservation(2,2,"2025-10-22"));
            // Novembre
            reservationDao.insert(new Reservation(3,3,"2025-11-05"));
            reservationDao.insert(new Reservation(1,1,"2025-11-25"));
            // Décembre
            reservationDao.insert(new Reservation(2,2,"2025-12-07"));
            reservationDao.insert(new Reservation(3,3,"2025-12-23"));
        }
    }
}
