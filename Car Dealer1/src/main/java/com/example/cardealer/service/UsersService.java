package com.example.cardealer.service;

import com.example.cardealer.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Optional<Users> findById(Long id);
    void saveUsers(Users user);


    //lấy tất cả
    List<Users> listsUsers();

    //tìm theo id
    Users findUserById(Long id);

    //delete
    void deleteByUser(Long id);

    Users findByUsername(String name);

    Boolean existsByUsername(String username);
}
