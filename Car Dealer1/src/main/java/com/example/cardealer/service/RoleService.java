package com.example.cardealer.service;

import com.example.cardealer.model.Roles;

import java.util.Optional;

public interface RoleService {

    Optional<Roles> findByRoleName(String name);
}
