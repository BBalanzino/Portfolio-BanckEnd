package com.backend.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.backend.portfolio.entity.Proyecto;
import com.backend.portfolio.entity.Usuario;
import com.backend.portfolio.services.ProyectoService;
import com.backend.portfolio.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    private final ProyectoService proyectoService;
    @Autowired
    private final UsuarioService usuarioService;

    public ProyectoController(ProyectoService proyectoService, UsuarioService usuarioService) {
        this.proyectoService = proyectoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Proyecto> getProyecto(@PathVariable("id") Long id) {
        try {
            Proyecto Proyecto = proyectoService.getProyectoById(id);
            return new ResponseEntity<>(Proyecto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/lista")
    public List<Proyecto> getAllProyectos() {
        return proyectoService.getProyectos();
    }

    @PostMapping("/crear")
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            proyecto.setUsuario(usuario);
        }

        return proyectoService.addProyecto(proyecto);
    }

    @PutMapping("/update")
    public Proyecto updateProyecto(@RequestBody Proyecto proyecto, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            proyecto.setUsuario(usuario);
        }

        return proyectoService.updateProyecto(proyecto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProyecto(@PathVariable("id") Long id) {
        try {
            Proyecto proyecto = proyectoService.getProyectoById(id);

            if (proyecto != null) {
                proyectoService.deleteProyectoById(id);
            }

            return new ResponseEntity<Long>(id, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Long>(0L, HttpStatus.BAD_REQUEST);
        }
    }

}
