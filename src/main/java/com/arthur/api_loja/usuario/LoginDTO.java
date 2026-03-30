package com.arthur.api_loja.usuario;

import lombok.Data;

@Data
public class LoginDTO {
    private  String email;
    private String senha;
}
