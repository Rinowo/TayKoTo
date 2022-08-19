package com.example.cardealer.repository;

import com.example.cardealer.model.VehicleGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GalleryRepository extends JpaRepository<VehicleGallery, Long> {

    Optional<VehicleGallery> findByVehicleId(Long vehicleId);

    List<VehicleGallery> getAllByVehicleId(Long vehicleId);
}
