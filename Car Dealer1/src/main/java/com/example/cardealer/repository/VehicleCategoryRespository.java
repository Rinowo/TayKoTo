package com.example.cardealer.repository;

import com.example.cardealer.model.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCategoryRespository
        extends JpaRepository<VehicleCategory, Long> {

}
