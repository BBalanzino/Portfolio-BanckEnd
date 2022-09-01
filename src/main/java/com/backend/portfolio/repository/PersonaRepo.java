package com.backend.portfolio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.portfolio.entity.Persona;

@Repository
public interface PersonaRepo extends JpaRepository<Persona, Long>{
    
}
