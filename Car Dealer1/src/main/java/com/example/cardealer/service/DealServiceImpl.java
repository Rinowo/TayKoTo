package com.example.cardealer.service;

import com.example.cardealer.model.Deal;
import com.example.cardealer.model.Vehicle;
import com.example.cardealer.repository.DealRepository;
import com.example.cardealer.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAllVehicle() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

    @Override
    public void save(Deal deal) {
        dealRepository.save(deal);
    }

    @Override
    public void delete(Long id) {
        dealRepository.deleteById(id);
    }

    @Override
    public Optional<Deal> findDeal(Long id) {
        return dealRepository.findById(id);
    }

    @Override
    public List<Deal> findAllByCustomerId(Long id) {
        return dealRepository.findAllByCustomerId(id);
    }
}
