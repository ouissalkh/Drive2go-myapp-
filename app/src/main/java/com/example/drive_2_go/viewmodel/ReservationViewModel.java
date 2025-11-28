package com.example.drive_2_go.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drive_2_go.data.model.Reservation;
import com.example.drive_2_go.data.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.Map;

public class ReservationViewModel extends ViewModel {

    private ReservationRepository repository = new ReservationRepository();

    // ---------------------------
    // 1️⃣ Liste complète des réservations
    // ---------------------------
    private MutableLiveData<ArrayList<Reservation>> reservationList = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Reservation>> getReservations() {
        repository.loadReservations(reservationList);
        return reservationList;
    }

    // ---------------------------
    // 2️⃣ Nombre de réservations par mois
    // ---------------------------
    private MutableLiveData<Map<Integer, Integer>> reservationsParMois = new MutableLiveData<>();

    public MutableLiveData<Map<Integer, Integer>> getReservationsParMois() {
        repository.loadReservationsCountByMonth(reservationsParMois);
        return reservationsParMois;
    }
}
