package com.arthur.api_loja.response;

public class ProdutoResponse {

    private  Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;

    public ProdutoResponse(Long id, String nome, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
