package com.example.cardealer.service;

import com.example.cardealer.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> getAll();

    void saveVehicle(Vehicle vehicle);

    void deleteVehicleId(Long id);

    Optional<Vehicle> findById(Long id);


}
