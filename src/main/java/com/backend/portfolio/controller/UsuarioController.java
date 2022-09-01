package com.backend.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.portfolio.entity.Usuario;
import com.backend.portfolio.models.LoginData;
import com.backend.portfolio.models.LoginResult;
import com.backend.portfolio.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // @PostMapping("/login")
    // public LoginResult login(@RequestBody LoginData loginData) {
    //     try {
    //         LoginResult result = this.usuarioService.login(loginData.username, loginData.password);

    //         if (result != null) {
    //             return result;
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Login error: " + e.getMessage());
    //         return null; // TODO: return something else
    //     }

    //     return null; // TODO: how can I send something else without ResponseEntity?
    // }

}
