package com.example.cardealer.service;

import com.example.cardealer.model.VehicleCategory;
import com.example.cardealer.repository.VehicleCategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCategoryServiceImpl implements VehicleCategoryService{

    @Autowired
    private VehicleCategoryRespository vehicleCategoryRespository;


    @Override
    public List<VehicleCategory> getAll() {
        return vehicleCategoryRespository.findAll();
    }

    @Override
    public void save(VehicleCategory vehicleCategory) {
        vehicleCategoryRespository.save(vehicleCategory);
    }
}
