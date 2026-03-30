package com.arthur.api_loja.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProdutoDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Double preco;

    @NotNull
    private Integer quantidade;
}
