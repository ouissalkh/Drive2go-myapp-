package com.example.drive_2_go.ui.Admin.Table_bord;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.drive_2_go.data.model.ReservationMonthCount;
import com.example.drive_2_go.data.repository.ReservationRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DashboardViewModel extends AndroidViewModel {

    private final ReservationRepository repository;
    private final MutableLiveData<List<ReservationMonthCount>> monthlyReservations = new MutableLiveData<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        repository = new ReservationRepository(application);
        loadMonthlyReservations();
    }

    // Méthode pour charger les données en arrière-plan
    private void loadMonthlyReservations() {
        executor.execute(() -> {
            List<ReservationMonthCount> list = repository.getReservationsPerMonth();
            monthlyReservations.postValue(list); // Met à jour le LiveData depuis le thread background
        });
    }

    // Getter pour l'Activity
    public LiveData<List<ReservationMonthCount>> getMonthlyReservations() {
        return monthlyReservations;
    }
}
