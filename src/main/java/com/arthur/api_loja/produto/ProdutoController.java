package com.arthur.api_loja.produto;

import com.arthur.api_loja.response.ApiResponse;
import com.arthur.api_loja.exception.ResourceNotFoundException;
import com.arthur.api_loja.response.ProdutoResponse;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service){
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Produto> criar(@RequestBody @Valid ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());

        Produto salvo = service.criar(produto);

        ProdutoResponse response = new ProdutoResponse(
                salvo.getId(),
                salvo.getNome(),
                salvo.getPreco(),
                salvo.getQuantidade()
        );

        return new ApiResponse<>(
                "sucesso",
                "Produto criado com sucesso",
                salvo
        );
    }

    @GetMapping
    public ApiResponse<List<ProdutoResponse>> listar() {

        List<ProdutoResponse> lista = service.listar()
                .stream()
                .map(p -> new ProdutoResponse(
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        p.getQuantidade()
                ))
                .toList();

        return new ApiResponse<>(
                "sucesso",
                "Produtos carregados com sucesso",
                lista
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProdutoResponse> buscar(@PathVariable Long id) {

        Produto produto = service.buscarPorId(id);

        if (produto == null) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        ProdutoResponse response = new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade()
        );

        return new ApiResponse<>(
                "sucesso",
                "Produto encontrado",
                response
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<ProdutoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoDTO dto
    ) {
        Produto produtoParaAtualizar = new Produto();
        produtoParaAtualizar.setNome(dto.getNome());
        produtoParaAtualizar.setPreco(dto.getPreco());
        produtoParaAtualizar.setQuantidade(dto.getQuantidade());

        Produto atualizado = service.atualizar(id, produtoParaAtualizar);

        if (atualizado == null) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        ProdutoResponse  response = new ProdutoResponse(
          atualizado.getId(),
                atualizado.getNome(),
                atualizado.getPreco(),
                atualizado.getQuantidade()
        );

        return new ApiResponse<>(
                "sucesso",
                "Produto atualizado com sucesso",
                response
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletar(@PathVariable Long id) {
        Produto produto = service.buscarPorId(id);

        if (produto == null) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        service.deletar(id);

        return new ApiResponse<>(
                "sucesso",
                "Produto deletado com sucesso",
                null
        );
    }
}