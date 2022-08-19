package com.example.cardealer.service;

import com.example.cardealer.model.InfoService;
import com.example.cardealer.repository.InfoServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InfoServiceImpl implements InfoServices {

    @Autowired
    private InfoServiceRepository infoServiceRepository;

    @Override
    public List<InfoService> getAll() {
        return infoServiceRepository.findAll();
    }

    @Override
    public void saveInfo(InfoService infoService) {
        infoServiceRepository.save(infoService);
    }

    @Override
    public void deleteInfo(Long vehicleId) {
        infoServiceRepository.deleteInfoServiceByVehicleId(vehicleId);
    }

    @Override
    public Optional<InfoService> findByVehicleId(Long vehicleId) {
        return infoServiceRepository.findByVehicleId(vehicleId);
    }

    @Override
    public InfoService getOne(Long id) {
        return infoServiceRepository.findByVehicleId(id).get();
    }
}
