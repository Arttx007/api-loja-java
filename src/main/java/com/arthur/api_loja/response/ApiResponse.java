package com.arthur.api_loja.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String status; // SUCESSO OU ERRO
    private String mensagem; // MENSAGEM QUE O FRONT VERA
    private T dados; // OBJETO REAL Q VC QUER RETORNAR ( PRODUTO , CLIENTE , ETC)
}
