package com.example.drive_2_go.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.drive_2_go.data.model.Car;
import com.example.drive_2_go.data.repository.CarRepository;
import java.util.List;

public class CarViewModel extends AndroidViewModel {

    private CarRepository repository;
    private MutableLiveData<List<Car>> carsLiveData;

    public CarViewModel(@NonNull Application application) {
        super(application);
        repository = new CarRepository(application);
        carsLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Car>> getCars() {
        loadCars();
        return carsLiveData;
    }

    private void loadCars() {
        List<Car> cars = repository.getAllCars();
        carsLiveData.postValue(cars);
    }
}
