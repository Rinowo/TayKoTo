package com.example.cardealer.service;

import com.example.cardealer.model.Deal;
import com.example.cardealer.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface DealService {

    List<Vehicle> findAllVehicle();

    List<Deal> findAll();

    void save(Deal deal);

    void delete(Long id);

    Optional<Deal> findDeal(Long id);

    List<Deal> findAllByCustomerId(Long id);
}
