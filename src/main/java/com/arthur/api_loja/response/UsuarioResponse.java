package com.arthur.api_loja.response;

public class UsuarioResponse {

    private  Long id;
    private String email;

    public UsuarioResponse(Long id, String email){
        this.id = id;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }
}
