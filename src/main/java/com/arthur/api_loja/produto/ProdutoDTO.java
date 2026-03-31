package com.arthur.api_loja.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Data
public class ProdutoDTO {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero(message = "O preco nao pode ser negativo")
    @DecimalMax(value = "100000.00", message = "O preco nao pode ser maior que 100000")
    private Double preco;

    @NotNull
    private Integer quantidade;

    private String imagemUrl;
}
