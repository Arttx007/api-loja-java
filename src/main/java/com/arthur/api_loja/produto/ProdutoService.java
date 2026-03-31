package com.arthur.api_loja.produto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // Atualiza um produto existente pelo ID
    public Produto atualizar(Long id, Produto produto) {
        Produto existente = repository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNome(produto.getNome());
            existente.setPreco(produto.getPreco());
            existente.setQuantidade(produto.getQuantidade());
            existente.setImagemUrl(produto.getImagemUrl());
            return repository.save(existente);
        }

        return null; // Se não encontrar
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
