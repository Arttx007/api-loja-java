package com.arthur.api_loja.response;

public class UsuarioResponse {

    private  Long id;
    private String nome;
    private String email;
    private String fotoUrl;
    private Double fotoZoom;
    private Integer fotoPosX;
    private Integer fotoPosY;
    private String role;

    public UsuarioResponse(Long id, String nome, String email, String fotoUrl, Double fotoZoom, Integer fotoPosX, Integer fotoPosY, String role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fotoUrl = fotoUrl;
        this.fotoZoom = fotoZoom;
        this.fotoPosX = fotoPosX;
        this.fotoPosY = fotoPosY;
        this.role = role;
    }

    public Long getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail(){
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
}
