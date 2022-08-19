package com.example.cardealer.repository;

import com.example.cardealer.model.InfoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoServiceRepository
        extends JpaRepository<InfoService, Long>  {

    List<InfoService> deleteInfoServiceByVehicleId(Long vehicleId);

    Optional<InfoService> findByVehicleId(Long vechicleId);
}
