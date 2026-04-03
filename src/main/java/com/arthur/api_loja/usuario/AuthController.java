package com.arthur.api_loja.usuario;

import com.arthur.api_loja.response.ApiResponse;
import com.arthur.api_loja.response.LoginResponse;
import com.arthur.api_loja.response.UsuarioResponse;
import com.arthur.api_loja.security.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService service;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginDTO dto) {
        Usuario usuario = service.login(dto);

        if (usuario == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>(
                    "erro",
                    "Email ou senha invalidos",
                    null
            ));
        }

        String token = jwtUtil.gerarToken(
                usuario.getEmail(),
                usuario.getRole()
        );

        LoginResponse response = new LoginResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getFotoUrl(),
                usuario.getFotoZoom(),
                usuario.getFotoPosX(),
                usuario.getFotoPosY(),
                usuario.getRole(),
                token
        );

        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Login realizado com sucesso",
                response
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsuarioResponse>> register(@RequestBody Usuario usuario) {
        usuario.setRole("CLIENTE");
        Usuario salvo = service.salvar(usuario);

        UsuarioResponse response = new UsuarioResponse(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getFotoUrl(),
                salvo.getFotoZoom(),
                salvo.getFotoPosX(),
                salvo.getFotoPosY(),
                salvo.getRole()
        );

        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Usuario registrado com sucesso",
                response
        ));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> listarUsuarios() {
        List<UsuarioResponse> usuarios = service.listarUsuarios()
                .stream()
                .map(u -> new UsuarioResponse(
                        u.getId(),
                        u.getNome(),
                        u.getEmail(),
                        u.getFotoUrl(),
                        u.getFotoZoom(),
                        u.getFotoPosX(),
                        u.getFotoPosY(),
                        u.getRole()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Usuarios carregados com sucesso",
                usuarios
        ));
    }

    @PostMapping("/usuarios")
    public ResponseEntity<ApiResponse<UsuarioResponse>> atualizarPorPost(
            @RequestBody Usuario usuarioAtualizado
    ) {
        if (usuarioAtualizado.getId() == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "erro",
                    "Id do usuario obrigatorio",
                    null
            ));
        }

        return montarRespostaAtualizacao(
                service.atualizar(usuarioAtualizado.getId(), usuarioAtualizado)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UsuarioResponse>> usuarioLogado(Authentication authentication) {
        Usuario usuario = service.buscarPorEmail(authentication.getName());

        if (usuario == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>(
                    "erro",
                    "Usuario nao encontrado",
                    null
            ));
        }

        UsuarioResponse response = new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getFotoUrl(),
                usuario.getFotoZoom(),
                usuario.getFotoPosX(),
                usuario.getFotoPosY(),
                usuario.getRole()
        );

        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Usuario carregado com sucesso",
                response
        ));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UsuarioResponse>> atualizarUsuarioLogado(
            Authentication authentication,
            @RequestBody Usuario usuarioAtualizado
    ) {
        return montarRespostaAtualizacao(
                service.atualizarConta(authentication.getName(), usuarioAtualizado)
        );
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> atualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuarioAtualizado
    ) {
        return montarRespostaAtualizacao(service.atualizar(id, usuarioAtualizado));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse<Void>> deletar(@PathVariable Long id) {
        boolean deletado = service.deletar(id);

        if (!deletado) {
            return ResponseEntity.status(404).body(new ApiResponse<>(
                    "erro",
                    "Usuario nao encontrado",
                    null
            ));
        }

        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Usuario deletado com sucesso",
                null
        ));
    }

    private ResponseEntity<ApiResponse<UsuarioResponse>> montarRespostaAtualizacao(Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>(
                    "erro",
                    "Usuario nao encontrado",
                    null
            ));
        }

        UsuarioResponse response = new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getFotoUrl(),
                usuario.getFotoZoom(),
                usuario.getFotoPosX(),
                usuario.getFotoPosY(),
                usuario.getRole()
        );

        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Usuario atualizado com sucesso",
                response
        ));
    }
}
