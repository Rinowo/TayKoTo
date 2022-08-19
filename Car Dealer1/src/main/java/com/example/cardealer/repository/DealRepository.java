package com.example.cardealer.repository;

import com.example.cardealer.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

    List<Deal> findAllByCustomerId(Long id);
}
