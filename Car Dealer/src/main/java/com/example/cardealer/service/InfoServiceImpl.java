package com.example.cardealer.service;

import com.example.cardealer.model.InfoService;
import com.example.cardealer.repository.InfoServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoServices {

    @Autowired
    private InfoServiceRepository infoServiceRepository;

    @Override
    public List<InfoService> getAll() {
        return infoServiceRepository.findAll();
    }
}
