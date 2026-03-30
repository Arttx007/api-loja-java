package com.arthur.api_loja.produto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    void criarProduto_deveRetornarProdutoSalvo() {
        Produto produto = new Produto();
        produto.setNome("Produto teste");
        produto.setPreco(10.0);
        produto.setQuantidade(5);

        when(repository.save(produto)).thenReturn(produto);

        Produto resultado = service.criar(produto);

        assertNotNull(resultado);
        assertEquals("Produto teste", resultado.getNome());
        verify(repository, times(1)).save(produto);
    }

    @Test
    void buscarPorId_quandoProdutoExistir_deveRetornarProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Existente");

        when(repository.findById(1L)).thenReturn(Optional.of(produto));

        Produto resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Produto Existente", resultado.getNome());
    }

    @Test
    void buscarPorId_quandoProdutoNaoExistir_deveLancarExcecao() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
        assertEquals("Produto não encontrado", exception.getMessage());
    }
}