package com.example.drive_2_go.data.repository;

import android.app.Application;

import com.example.drive_2_go.data.dao.ReservationDao;
import com.example.drive_2_go.data.database.AppDatabase;
import com.example.drive_2_go.data.model.ReservationMonthCount;

import java.util.List;

public class ReservationRepository {

    private final ReservationDao reservationDao;

    public ReservationRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        reservationDao = db.reservationDao();
    }

    // Retourne directement la liste des reservations par mois
    public List<ReservationMonthCount> getReservationsPerMonth() {
        return reservationDao.getReservationsPerMonth();
    }
}
