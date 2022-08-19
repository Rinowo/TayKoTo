package com.example.cardealer.service;

import com.example.cardealer.model.UsersRole;
import com.example.cardealer.repository.UsersRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersRoleServiceImpl implements UsersRoleService {

    @Autowired
    private UsersRoleRepository usersRoleRepository;

    @Override
    public Optional<UsersRole> findUserRoleByUserId(Long id) {
        return usersRoleRepository.findUserRoleByUserId(id);
    }

    @Override
    public void deleteById(Long id) {
        usersRoleRepository.deleteById(id);
    }
}
