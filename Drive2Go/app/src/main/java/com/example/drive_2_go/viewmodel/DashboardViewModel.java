package com.example.drive_2_go.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.drive_2_go.data.repository.DashboardRepository;

import java.util.Map;

public class DashboardViewModel extends ViewModel {

    private final DashboardRepository repository;
    private LiveData<Map<Integer, Integer>> reservationsParMois;

    public DashboardViewModel() {
        repository = new DashboardRepository();
    }

    public LiveData<Map<Integer, Integer>> getReservationsParMois() {
        if (reservationsParMois == null) {
            reservationsParMois = repository.getReservationParMois();
        }
        return reservationsParMois;
    }
}
