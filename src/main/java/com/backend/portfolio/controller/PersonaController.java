package com.backend.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.portfolio.entity.Persona;
import com.backend.portfolio.entity.Usuario;
import com.backend.portfolio.services.PersonaService;
import com.backend.portfolio.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @Autowired
    private UsuarioService usuarioService;

    public PersonaController(PersonaService personaService, UsuarioService usuarioService) {
        this.personaService = personaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Persona> getPersona(@PathVariable("id") Long id) {
        try {
            Persona Persona = personaService.getPersonaById(id);
            return new ResponseEntity<>(Persona, HttpStatus.OK);    
        } catch(NotFoundException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } 
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        return new ResponseEntity<String>(userDetails.getUsername(), HttpStatus.OK); 
    }

    @PutMapping("/update")
    public Persona updatePersona(@RequestBody Persona persona, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            persona.setUsuario(usuario);
        }

        return personaService.updatePersona(persona);
    }
}
 