package com.example.drive_2_go.data.dao;

import com.example.drive_2_go.data.model.Car;
import java.util.List;

public interface CarDao {
    void insert(Car car);
    void update(Car car);
    void delete(int id);
    List<Car> getAll();
}
