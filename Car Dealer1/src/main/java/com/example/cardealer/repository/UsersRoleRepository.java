package com.example.cardealer.repository;

import com.example.cardealer.model.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRoleRepository extends JpaRepository<UsersRole, Long> {

    Optional<UsersRole> findUserRoleByUserId(Long id);

    void deleteById(Long id);
}
