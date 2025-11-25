package com.example.drive_2_go.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drive_2_go.data.model.Car;
import com.example.drive_2_go.data.repository.CarRepository;

import java.util.List;

public class CarViewModel extends AndroidViewModel {

    private CarRepository repository;
    private LiveData<List<Car>> allCars;

    public CarViewModel(@NonNull Application application) {
        super(application);
        repository = new CarRepository(application);
        allCars = repository.getAllCars();
    }

    public LiveData<List<Car>> getAllCars() {
        return allCars;
    }

    public void insert(Car car) {
        repository.insert(car);
    }

    public void update(Car car) {
        repository.update(car);
    }

    public void delete(Car car) {
        repository.delete(car);
    }
}
