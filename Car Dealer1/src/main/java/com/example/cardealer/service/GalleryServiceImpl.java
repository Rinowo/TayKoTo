package com.example.cardealer.service;

import com.example.cardealer.model.VehicleGallery;
import com.example.cardealer.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    @Override
    public List<VehicleGallery> getAll() {
        return galleryRepository.findAll();
    }

    @Override
    public void saveImg(VehicleGallery vehicleGallery) {
        galleryRepository.save(vehicleGallery);
    }

    @Override
    public Optional<VehicleGallery> findByVehicleId(Long vehicleId) {
        return galleryRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<VehicleGallery> getAllByVehicleId(Long vehicleId) {
        return galleryRepository.getAllByVehicleId(vehicleId);
    }
}
