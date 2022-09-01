package com.backend.portfolio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.portfolio.entity.Experiencia;
import com.backend.portfolio.repository.ExperienciaRepo;

@Service
@Transactional
public class ExperienciaService {
    
    @Autowired
    private ExperienciaRepo expRepo;

    public List<Experiencia> getExperiencias() {
        return expRepo.findAll();
    }

    public Experiencia updateExperiencia(Experiencia updatedExp){
        Optional<Experiencia> exp = expRepo.findById(updatedExp.getId());
        if(exp == null){
          return null;  
        }
        return expRepo.save(updatedExp);
    }

    public Experiencia getExperienciaById(Long id) throws NotFoundException{
        return expRepo.findById(id).orElseThrow(()-> new NotFoundException());
    }

    public Experiencia addExperiencia(Experiencia nuevaExperiencia){
        return expRepo.save(nuevaExperiencia);
    }

    public void deleteExpById(Long id){
        expRepo.deleteById(id);
    }
}
