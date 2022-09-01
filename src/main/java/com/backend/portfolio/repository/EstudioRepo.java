package com.backend.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.portfolio.entity.Estudio;

@Repository
public interface EstudioRepo extends JpaRepository<Estudio, Long>{
    
}
