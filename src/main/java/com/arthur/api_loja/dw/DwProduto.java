package com.arthur.api_loja.dw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dw_produtos")
@Getter
@Setter
public class DwProduto {

    @Id
    private Long produtoId;

    private String nome;
    private Double preco;
    private Integer quantidade;
    private Boolean disponivel;
    private String imagemUrl;
}
