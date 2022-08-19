package com.example.cardealer.service;

import com.example.cardealer.model.VehicleGallery;

import java.util.List;
import java.util.Optional;

public interface GalleryService {

    List<VehicleGallery> getAll();

    void saveImg(VehicleGallery vehicleGallery);

    Optional<VehicleGallery> findByVehicleId(Long vehicleId);

    List<VehicleGallery> getAllByVehicleId(Long vehicleId);
}
