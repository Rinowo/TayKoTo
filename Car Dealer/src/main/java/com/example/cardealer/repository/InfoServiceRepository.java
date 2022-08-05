package com.example.cardealer.repository;

import com.example.cardealer.model.InfoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoServiceRepository
        extends JpaRepository<InfoService, Long>  {

}
