package com.example.cardealer.service;

import com.example.cardealer.model.VehicleCategory;

import java.util.List;

public interface VehicleCategoryService {

    List<VehicleCategory> getAll();

    void save(VehicleCategory vehicleCategory);
}
