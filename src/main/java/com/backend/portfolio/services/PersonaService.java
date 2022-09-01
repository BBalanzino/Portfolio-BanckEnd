
package com.backend.portfolio.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.backend.portfolio.entity.Persona;
import com.backend.portfolio.repository.PersonaRepo;

@Service
@Transactional
public class PersonaService {
    @Autowired
    private PersonaRepo personaRepo;

    public List<Persona> getPersonas() {
        return personaRepo.findAll();
    }

    public Persona updatePersona(Persona updatedPersona) {
        Optional<Persona> persona = personaRepo.findById(updatedPersona.getId());

        if (persona == null) {
            return null;
        }

        return personaRepo.save(updatedPersona);
    }

    public Persona getPersonaById(Long id) throws NotFoundException{
        return personaRepo.findById(id).orElseThrow(()-> new NotFoundException());
    }

    public Persona addPersona(Persona nuevaPersona){
        return personaRepo.save(nuevaPersona);
    }

}
