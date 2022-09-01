package com.backend.portfolio.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.backend.portfolio.entity.Estudio;
import com.backend.portfolio.repository.EstudioRepo;

@Service
@Transactional
public class EstudioService {

    @Autowired//Repositorio de Estudios
    private EstudioRepo estudioRepo;

    //TRAER TODOS LOS ESTUDIOS
    public List<Estudio> getEstudios() {
        return estudioRepo.findAll();
    }
    //MODIFICAR UN ESTUDIO POR ID
    public Estudio updateEstudioById(Long id, Estudio updatedEstudio){
        Optional<Estudio> estudio = estudioRepo.findById(id);
        if(estudio == null){
          return null;  
        }
        return estudioRepo.save(updatedEstudio);
    }
    //TRAER UN ESTUDIO POR ID
    public Estudio getEstudioById(Long id) throws NotFoundException{
        return estudioRepo.findById(id).orElseThrow(()-> new NotFoundException());
    }
    //AGREGAR UN ESTUDIO NUEVO
    public Estudio addEstudio(Estudio nuevoEstudio){
        return estudioRepo.save(nuevoEstudio);
    }
    //BORRAR UN ESTUDIO POR ID
    public void deleteEstudioById(Long id){
        estudioRepo.deleteById(id);
    }
    public Estudio updateEstudio(Estudio updatedEstudio){
        Optional<Estudio> exp = estudioRepo.findById(updatedEstudio.getId());
        if(exp == null){
          return null;  
        }
        return estudioRepo.save(updatedEstudio);
    }



}
