package com.arthur.api_loja.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity // Diz que isso é uma tabela no banco
@Table(name = "produtos") // NOME DA TABELA
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Long id;

    private String nome;

    private Double preco;

    private Integer quantidade;

    @Column(name = "disponivel")
    private Boolean disponivel;

    @Column(name = "imagem_url")
    private String imagemUrl;
}
