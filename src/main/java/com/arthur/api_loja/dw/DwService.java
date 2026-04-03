package com.arthur.api_loja.dw;

import com.arthur.api_loja.produto.Produto;
import com.arthur.api_loja.produto.ProdutoRepository;
import com.arthur.api_loja.usuario.Usuario;
import com.arthur.api_loja.usuario.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DwService {

    private static final String DEFAULT_IMAGE =
            "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=800&q=80";

    private final DwProdutoRepository dwProdutoRepository;
    private final DwClienteRepository dwClienteRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public DwService(
            DwProdutoRepository dwProdutoRepository,
            DwClienteRepository dwClienteRepository,
            ProdutoRepository produtoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.dwProdutoRepository = dwProdutoRepository;
        this.dwClienteRepository = dwClienteRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostConstruct
    public void carregarDwInicial() {
        produtoRepository.findAll().forEach(this::sincronizarProduto);
        usuarioRepository.findAll().forEach(this::sincronizarCliente);
    }

    public void sincronizarProduto(Produto produto) {
        DwProduto dwProduto = new DwProduto();
        dwProduto.setProdutoId(produto.getId());
        dwProduto.setNome(produto.getNome());
        dwProduto.setPreco(produto.getPreco());
        dwProduto.setQuantidade(produto.getQuantidade());
        dwProduto.setDisponivel(produto.getQuantidade() != null && produto.getQuantidade() > 0);
        dwProduto.setImagemUrl(
                produto.getImagemUrl() == null || produto.getImagemUrl().isBlank()
                        ? DEFAULT_IMAGE
                        : produto.getImagemUrl()
        );
        dwProdutoRepository.save(dwProduto);
    }

    public void removerProduto(Long produtoId) {
        dwProdutoRepository.deleteById(produtoId);
    }

    public List<DwProdutoCatalogoResponse> listarCatalogoProdutos() {
        return dwProdutoRepository.findAll()
                .stream()
                .sorted((a, b) -> {
                    if (a.getNome() == null && b.getNome() == null) {
                        return 0;
                    }
                    if (a.getNome() == null) {
                        return 1;
                    }
                    if (b.getNome() == null) {
                        return -1;
                    }
                    return a.getNome().compareToIgnoreCase(b.getNome());
                })
                .map(produto -> new DwProdutoCatalogoResponse(
                        produto.getProdutoId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade(),
                        produto.getDisponivel(),
                        produto.getImagemUrl()
                ))
                .toList();
    }

    public void sincronizarCliente(Usuario usuario) {
        DwCliente dwCliente = new DwCliente();
        dwCliente.setUsuarioId(usuario.getId());
        dwCliente.setNome(usuario.getNome());
        dwCliente.setEmail(usuario.getEmail());
        dwCliente.setRole(usuario.getRole());
        dwCliente.setAtivo(true);
        dwClienteRepository.save(dwCliente);
    }

    public void desativarCliente(Long usuarioId) {
        dwClienteRepository.findById(usuarioId).ifPresent(cliente -> {
            cliente.setAtivo(false);
            dwClienteRepository.save(cliente);
        });
    }
}
