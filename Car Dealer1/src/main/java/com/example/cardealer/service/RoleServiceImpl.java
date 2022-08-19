package com.example.cardealer.service;

import com.example.cardealer.model.Roles;
import com.example.cardealer.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Roles> findByRoleName(String name) {
        return roleRepository.findByRoleName(name);
    }
}
