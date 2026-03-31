package com.arthur.api_loja.usuario;

import com.arthur.api_loja.response.ApiResponse;
import com.arthur.api_loja.response.UsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UsuarioResponse>> login(@RequestBody LoginDTO dto){
        Usuario usuario = service.login(dto);

        if (usuario == null){
            return ResponseEntity.status(401).body(new ApiResponse<>(
                    "erro",
                    "Email ou senha inválidos",
                    null
            ));
        }

        UsuarioResponse response = new UsuarioResponse(
                usuario.getId(),
                usuario.getEmail()
        );

        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Login realizado com sucesso",
                response
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsuarioResponse>> register(@RequestBody Usuario usuario){
        Usuario salvo = service.salvar(usuario);
        UsuarioResponse response = new UsuarioResponse(salvo.getId(), salvo.getEmail());
        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Usuário registrado com sucesso",
                response
        ));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> listarUsuarios() {
        List<UsuarioResponse> usuarios = service.listarUsuarios()
                .stream()
                .map(u -> new UsuarioResponse(u.getId(), u.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Usuários carregados com sucesso",
                usuarios
        ));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse<Void>> deletar(@PathVariable Long id){

        boolean deletado = service.deletar(id);

        if(!deletado){
            return ResponseEntity.status(404).body(new ApiResponse<>(
                    "erro",
                    "Usuário não encontrado",
                    null
            ));
        }
        return ResponseEntity.ok(new ApiResponse<>(
                "sucesso",
                "Usuário deletado com sucesso",
                null
        ));
    }
}
