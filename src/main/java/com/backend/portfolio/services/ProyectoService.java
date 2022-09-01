package com.backend.portfolio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.portfolio.entity.Proyecto;
import com.backend.portfolio.repository.ProyectoRepo;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    private ProyectoRepo proyectoRepo;

    // TRAER TODOS LOS ProyectoS
    public List<Proyecto> getProyectos() {
        return proyectoRepo.findAll();
    }

    // MODIFICAR UN Proyecto
    public Proyecto updateProyecto(Proyecto updatedProyecto) {
        Optional<Proyecto> proyecto = proyectoRepo.findById(updatedProyecto.getId());

        if (proyecto == null) {
            return null;
        }

        return proyectoRepo.save(updatedProyecto);
    }

    // TRAER UN Proyecto POR ID
    public Proyecto getProyectoById(Long id) throws NotFoundException {
        return proyectoRepo.findById(id).orElseThrow(() -> new NotFoundException());
    }

    // AGREGAR UN Proyecto NUEVO
    public Proyecto addProyecto(Proyecto nuevoProyecto) {
        return proyectoRepo.save(nuevoProyecto);
    }

    // BORRAR UN Proyecto POR ID
    public void deleteProyectoById(Long id) {
        proyectoRepo.deleteById(id);
    }

}
