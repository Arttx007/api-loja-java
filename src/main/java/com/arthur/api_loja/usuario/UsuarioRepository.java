package com.arthur.api_loja.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findTopByEmailOrderByIdDesc(String email);
    Optional<Usuario> findTopByNomeOrderByIdDesc(String nome);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
