package com.arthur.api_loja.produto;

import com.arthur.api_loja.dw.DwService;
import com.arthur.api_loja.exception.ResourceNotFoundException;
import com.arthur.api_loja.response.ApiResponse;
import com.arthur.api_loja.response.ProdutoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/produtos", "/produto"})
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService service;
    private final DwService dwService;

    public ProdutoController(ProdutoService service, DwService dwService) {
        this.service = service;
        this.dwService = dwService;
    }

    @PostMapping
    public ApiResponse<Produto> criar(@RequestBody @Valid ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setImagemUrl(dto.getImagemUrl());

        Produto salvo = service.criar(produto);
        dwService.sincronizarProduto(salvo);

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
                        p.getQuantidade(),
                        p.getImagemUrl()
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

        ProdutoResponse response = new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getImagemUrl()
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
        produtoParaAtualizar.setImagemUrl(dto.getImagemUrl());

        Produto atualizado = service.atualizar(id, produtoParaAtualizar);

        if (atualizado == null) {
            throw new ResourceNotFoundException("Produto nao encontrado");
        }

        dwService.sincronizarProduto(atualizado);

        ProdutoResponse response = new ProdutoResponse(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getPreco(),
                atualizado.getQuantidade(),
                atualizado.getImagemUrl()
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
            throw new ResourceNotFoundException("Produto nao encontrado");
        }

        service.deletar(id);
        dwService.removerProduto(id);

        return new ApiResponse<>(
                "sucesso",
                "Produto deletado com sucesso",
                null
        );
    }
}
