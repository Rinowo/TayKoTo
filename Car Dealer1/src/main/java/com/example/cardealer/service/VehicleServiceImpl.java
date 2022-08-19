package com.example.cardealer.service;

import com.example.cardealer.model.Vehicle;
import com.example.cardealer.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicleId(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Page<Vehicle> findVehicle(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }


}
