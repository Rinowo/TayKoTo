package com.example.cardealer.service;

import com.example.cardealer.model.InfoService;

import java.util.List;
import java.util.Optional;

public interface InfoServices {

    List<InfoService> getAll();

    void saveInfo(InfoService infoService);

    void deleteInfo(Long vehicleId);

    Optional<InfoService> findByVehicleId(Long vehicleId);

    InfoService getOne(Long id);
}
