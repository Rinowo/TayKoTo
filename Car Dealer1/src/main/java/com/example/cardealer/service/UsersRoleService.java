package com.example.cardealer.service;

import com.example.cardealer.model.UsersRole;

import java.util.Optional;

public interface UsersRoleService {
    Optional<UsersRole> findUserRoleByUserId(Long id);

    void deleteById(Long id);
}
