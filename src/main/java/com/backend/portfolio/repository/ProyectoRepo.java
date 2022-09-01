package com.backend.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.portfolio.entity.Proyecto;
@Repository
public interface ProyectoRepo extends JpaRepository<Proyecto, Long>{
    
}
