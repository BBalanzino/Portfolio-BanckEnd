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

import com.backend.portfolio.entity.Skill;
import com.backend.portfolio.entity.Usuario;
import com.backend.portfolio.services.SkillService;
import com.backend.portfolio.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private final SkillService skillService;

    @Autowired
    private final UsuarioService usuarioService;

    public SkillController(SkillService skillService, UsuarioService usuarioService) {
        this.skillService = skillService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Skill> getSkill(@PathVariable("id") Long id) {
        try {
            Skill skill = skillService.getSkillById(id);
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/lista")
    public List<Skill> getAllExperiencia() {
        return skillService.getSkills();
    }

    @PostMapping("/crear")
    public Skill crearSkill(@RequestBody Skill skill, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            skill.setUsuario(usuario);
        }

        return skillService.addSkill(skill);
    }

    @PutMapping("/update")
    public Skill updateSkill(@RequestBody Skill skill,
            @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findUsuarioByUsername(userDetails.getUsername());

        if (usuario != null) {
            skill.setUsuario(usuario);
        }

        return skillService.updateSkill(skill);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteSkill(@PathVariable("id") Long id) {
        try {
            Skill skill = skillService.getSkillById(id);

            if (skill != null) {
                skillService.deleteSkillById(id);
            }

            return new ResponseEntity<Long>(id, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Long>(0L, HttpStatus.BAD_REQUEST);
        }
    }
}
