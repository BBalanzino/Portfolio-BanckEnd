package com.backend.portfolio.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
import com.backend.portfolio.entity.Usuario;
import com.backend.portfolio.exceptions.UserNotFoundException;
import com.backend.portfolio.models.LoginResult;
import com.backend.portfolio.repository.UsuarioRepo;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    public Usuario findUsuarioById(Long id) {
        return usuarioRepo.findById(id).orElseThrow(() -> new UserNotFoundException("No se encontro el usuario"));
    }

    public Usuario findUsuarioByUsername(String username) {
        return usuarioRepo.findByUsername(username);
    }
}
