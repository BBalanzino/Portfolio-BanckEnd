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

import com.backend.portfolio.entity.Experiencia;
import com.backend.portfolio.entity.Usuario;
import com.backend.portfolio.services.ExperienciaService;
import com.backend.portfolio.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/experiencias")
public class ExperienciaController {
    @Autowired
    private final ExperienciaService expService;
    @Autowired
    private final UsuarioService usuarioService;

    public ExperienciaController(ExperienciaService expService, UsuarioService usuarioService) {
        this.expService = expService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Experiencia> getExperiencia(@PathVariable("id") Long id) {
        try {
            Experiencia exp = expService.getExperienciaById(id);
            return new ResponseEntity<>(exp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/lista")
    public List<Experiencia> getAllExperiencia() {
        return expService.getExperiencias();
    }

    @PostMapping("/crear")
    public Experiencia crearSkill(@RequestBody Experiencia exp, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            exp.setUsuario(usuario);
        }

        return expService.addExperiencia(exp);
    }

    @PutMapping("/update")
    public Experiencia updateExperiencia(@RequestBody Experiencia exp,
            @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            exp.setUsuario(usuario);
        }

        return expService.updateExperiencia(exp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteExperiencia(@PathVariable("id") Long id) {
        try {
            Experiencia exp = expService.getExperienciaById(id);

            if (exp != null) {
                expService.deleteExpById(id);
            }

            return new ResponseEntity<Long>(id, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Long>(0L, HttpStatus.BAD_REQUEST);
        }
    }

}
