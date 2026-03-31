package com.arthur.api_loja.dw;

public class DwProdutoCatalogoResponse {

    private final Long id;
    private final String nome;
    private final Double preco;
    private final Integer quantidade;
    private final Boolean disponivel;
    private final String imagemUrl;

    public DwProdutoCatalogoResponse(
            Long id,
            String nome,
            Double preco,
            Integer quantidade,
            Boolean disponivel,
            String imagemUrl
    ) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.disponivel = disponivel;
        this.imagemUrl = imagemUrl;
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

    public Boolean getDisponivel() {
        return disponivel;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }
}
