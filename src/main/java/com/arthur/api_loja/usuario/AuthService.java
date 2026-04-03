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
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obrigatorio");
        }

        if (repository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ja existe um usuario com este email");
        }

        if (usuario.getRole() == null || usuario.getRole().isBlank()) {
            usuario.setRole("CLIENTE");
        }

        if (usuario.getFotoZoom() == null || usuario.getFotoZoom() <= 0) {
            usuario.setFotoZoom(1.0);
        }

        if (usuario.getFotoPosX() == null) {
            usuario.setFotoPosX(50);
        }

        if (usuario.getFotoPosY() == null) {
            usuario.setFotoPosY(50);
        }

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        Usuario salvo = repository.save(usuario);
        dwService.sincronizarCliente(salvo);
        return salvo;
    }

    public Usuario login(LoginDTO dto) {
        String identificador = dto.getEmail() == null ? "" : dto.getEmail().trim();

        Usuario usuario = repository.findTopByEmailOrderByIdDesc(identificador)
                .or(() -> repository.findTopByNomeOrderByIdDesc(identificador))
                .orElse(null);

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

        if (dadosAtualizados.getNome() != null && !dadosAtualizados.getNome().isBlank()) {
            usuario.setNome(dadosAtualizados.getNome());
        }

        if (dadosAtualizados.getEmail() != null && !dadosAtualizados.getEmail().isBlank()) {
            String emailAtualizado = dadosAtualizados.getEmail().trim();
            boolean mudouEmail = usuario.getEmail() == null
                    || !usuario.getEmail().equalsIgnoreCase(emailAtualizado);

            if (mudouEmail && repository.existsByEmailAndIdNot(emailAtualizado, id)) {
                throw new IllegalArgumentException("Ja existe um usuario com este email");
            }

            usuario.setEmail(emailAtualizado);
        }

        if (dadosAtualizados.getRole() != null && !dadosAtualizados.getRole().isBlank()) {
            usuario.setRole(dadosAtualizados.getRole());
        }

        if (dadosAtualizados.getFotoUrl() != null) {
            String fotoUrlAtualizada = dadosAtualizados.getFotoUrl().trim();
            usuario.setFotoUrl(fotoUrlAtualizada.isBlank() ? null : fotoUrlAtualizada);
        }

        if (dadosAtualizados.getFotoZoom() != null) {
            usuario.setFotoZoom(dadosAtualizados.getFotoZoom());
        }

        if (dadosAtualizados.getFotoPosX() != null) {
            usuario.setFotoPosX(dadosAtualizados.getFotoPosX());
        }

        if (dadosAtualizados.getFotoPosY() != null) {
            usuario.setFotoPosY(dadosAtualizados.getFotoPosY());
        }

        if (dadosAtualizados.getSenha() != null && !dadosAtualizados.getSenha().isBlank()) {
            usuario.setSenha(encoder.encode(dadosAtualizados.getSenha()));
        }

        Usuario atualizado = repository.save(usuario);
        dwService.sincronizarCliente(atualizado);
        return atualizado;
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findTopByEmailOrderByIdDesc(email).orElse(null);
    }

    public Usuario atualizarConta(String email, Usuario dadosAtualizados) {
        Usuario usuario = buscarPorEmail(email);

        if (usuario == null) {
            return null;
        }

        return atualizar(usuario.getId(), dadosAtualizados);
    }
}
