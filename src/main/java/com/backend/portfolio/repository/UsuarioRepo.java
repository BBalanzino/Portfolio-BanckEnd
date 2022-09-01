package com.backend.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.portfolio.entity.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long>{
    @Query(value="select * from usuario t where t.username = :username", nativeQuery = true)
    Usuario findByUsername(@Param("username") String username);
}
