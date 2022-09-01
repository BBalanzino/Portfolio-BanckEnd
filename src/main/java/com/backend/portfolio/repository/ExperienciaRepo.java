package com.backend.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.portfolio.entity.Experiencia;
@Repository
public interface ExperienciaRepo extends JpaRepository<Experiencia, Long>{

}
