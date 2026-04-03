package com.arthur.api_loja.response;

public class LoginResponse {

    private final Long id;
    private final String nome;
    private final String email;
    private final String fotoUrl;
    private final Double fotoZoom;
    private final Integer fotoPosX;
    private final Integer fotoPosY;
    private final String role;
    private final String token;

    public LoginResponse(Long id, String nome, String email, String fotoUrl, Double fotoZoom, Integer fotoPosX, Integer fotoPosY, String role, String token) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fotoUrl = fotoUrl;
        this.fotoZoom = fotoZoom;
        this.fotoPosX = fotoPosX;
        this.fotoPosY = fotoPosY;
        this.role = role;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public Double getFotoZoom() {
        return fotoZoom;
    }

    public Integer getFotoPosX() {
        return fotoPosX;
    }

    public Integer getFotoPosY() {
        return fotoPosY;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}
