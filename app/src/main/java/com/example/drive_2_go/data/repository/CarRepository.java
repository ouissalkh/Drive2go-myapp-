package com.example.drive_2_go.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.drive_2_go.data.dao.CarDao;
import com.example.drive_2_go.data.database.AppDatabase;
import com.example.drive_2_go.data.model.Car;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarRepository {

    private final CarDao carDao;
    private final MutableLiveData<List<Car>> allCars = new MutableLiveData<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public CarRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        carDao = database.carDao();
        refresh();
    }

    public LiveData<List<Car>> getAllCars() {
        return allCars;
    }

    public void insert(Car car) {
        executor.execute(() -> {
            carDao.insert(car);
            refresh();
        });
    }

    public void update(Car car) {
        executor.execute(() -> {
            carDao.update(car);
            refresh();
        });
    }

    public void delete(Car car) {
        executor.execute(() -> {
            carDao.delete(car.getId());
            refresh();
        });
    }

    private void refresh() {
        executor.execute(() -> allCars.postValue(carDao.getAll()));
    }
}
