package com.example.cardealer.repository;

import com.example.cardealer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String name);
    Boolean existsByUsername(String username);
}
