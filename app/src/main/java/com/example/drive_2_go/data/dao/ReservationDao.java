package com.example.drive_2_go.data.dao;

import com.example.drive_2_go.data.model.Reservation;
import com.example.drive_2_go.data.model.ReservationMonthCount;

import java.util.List;

public interface ReservationDao {
    void insert(Reservation reservation);

    List<Reservation> getAllReservations();

    int getReservationsCount();

    List<ReservationMonthCount> getReservationsPerMonth();
}
