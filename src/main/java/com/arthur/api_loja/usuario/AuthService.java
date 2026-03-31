package com.arthur.api_loja.usuario;

import com.arthur.api_loja.dw.DwService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final DwService dwService;

    public AuthService(UsuarioRepository repository, BCryptPasswordEncoder encoder, DwService dwService) {
        this.repository = repository;
        this.encoder = encoder;
        this.dwService = dwService;
    }

    public Usuario salvar(Usuario usuario) {
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ja existe um usuario com este email");
        }

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        Usuario salvo = repository.save(usuario);
        dwService.sincronizarCliente(salvo);
        return salvo;
    }

    public Usuario login(LoginDTO dto) {
        Usuario usuario = repository.findTopByEmailOrderByIdDesc(dto.getEmail()).orElse(null);

        if (usuario == null) {
            return null;
        }

        if (!encoder.matches(dto.getSenha(), usuario.getSenha())) {
            return null;
        }

        if (usuario.getRole() == null || usuario.getRole().isBlank()) {
            usuario.setRole("CLIENTE");
            usuario = repository.save(usuario);
        }

        dwService.sincronizarCliente(usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public boolean deletar(Long id) {
        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario == null) {
            return false;
        }

        repository.deleteById(id);
        dwService.desativarCliente(id);
        return true;
    }

    public Usuario atualizar(Long id, Usuario dadosAtualizados) {
        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario == null) {
            return null;
        }

        usuario.setEmail(dadosAtualizados.getEmail());
        usuario.setRole(dadosAtualizados.getRole());

        if (dadosAtualizados.getSenha() != null && !dadosAtualizados.getSenha().isBlank()) {
            usuario.setSenha(encoder.encode(dadosAtualizados.getSenha()));
        }

        Usuario atualizado = repository.save(usuario);
        dwService.sincronizarCliente(atualizado);
        return atualizado;
    }
}
