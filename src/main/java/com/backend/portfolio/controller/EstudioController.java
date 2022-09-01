package com.backend.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.portfolio.entity.Estudio;
import com.backend.portfolio.entity.Usuario;
import com.backend.portfolio.services.EstudioService;
import com.backend.portfolio.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/estudio")
public class EstudioController {
    @Autowired
    private final EstudioService estudioService;
    @Autowired
    private final UsuarioService usuarioService;


    public EstudioController(EstudioService estudioService, UsuarioService usuarioService) {
        this.estudioService = estudioService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Estudio> getEstudio(@PathVariable("id") Long id) throws NotFoundException {
        try {
            Estudio estudio = estudioService.getEstudioById(id);
            return new ResponseEntity<>(estudio, HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("/lista")
    public List<Estudio> getAllEstudios(){
        return estudioService.getEstudios();                     
    }

    @PostMapping("/crear")
    public Estudio crearEstudio(@RequestBody Estudio estudio, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            estudio.setUsuario(usuario);
        }

        return estudioService.addEstudio(estudio);
    }

    @PutMapping("/update")
    public Estudio updateEstudio(@RequestBody Estudio estudio,
            @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            estudio.setUsuario(usuario);
        }

        return estudioService.updateEstudio(estudio);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteEstudio(@PathVariable("id") Long id) {
        try {
            Estudio exp = estudioService.getEstudioById(id);

            if (exp != null) {
                estudioService.deleteEstudioById(id);
            }

            return new ResponseEntity<Long>(id, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Long>(0L, HttpStatus.BAD_REQUEST);
        }
    }
    
}
