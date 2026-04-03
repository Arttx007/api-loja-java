package com.arthur.api_loja.usuario;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String fotoUrl;
    private Double fotoZoom;
    private Integer fotoPosX;
    private Integer fotoPosY;

    private String role;
}
