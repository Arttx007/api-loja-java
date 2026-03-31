package com.arthur.api_loja.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findTopByEmailOrderByIdDesc(String email);
    boolean existsByEmail(String email);
}
