package com.backend.portfolio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.portfolio.entity.Skill;
import com.backend.portfolio.repository.SkillRepo;

@Service
@Transactional
public class SkillService {
    @Autowired
    private SkillRepo skillRepo;

    //TRAER TODOS LOS SkillS
    public List<Skill> getSkills() {
        return skillRepo.findAll();
    }
    //MODIFICAR UN Skill POR ID
    public Skill updateSkill(Skill updatedSkill){
        Optional<Skill> exp = skillRepo.findById(updatedSkill.getId());
        if(exp == null){
          return null;  
        }
        return skillRepo.save(updatedSkill);
    }
    //TRAER UN Skill POR ID
    public Skill getSkillById(Long id) throws NotFoundException{
        return skillRepo.findById(id).orElseThrow(()-> new NotFoundException());
    }
    //AGREGAR UN Skill NUEVO
    public Skill addSkill(Skill nuevoSkill){
        return skillRepo.save(nuevoSkill);
    }
    //BORRAR UN Skill POR ID
    public void deleteSkillById(Long id){
        skillRepo.deleteById(id);
    }
}
