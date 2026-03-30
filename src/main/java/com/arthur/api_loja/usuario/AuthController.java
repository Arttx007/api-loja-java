package com.arthur.api_loja.usuario;



import com.arthur.api_loja.response.UsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto){
        Usuario usuario = service.login(dto);

        if (usuario == null){
            return ResponseEntity.status(401).body("Email ou senha invalidos");
        }

        UsuarioResponse response = new UsuarioResponse(
                usuario.getId(),
                usuario.getEmail()
        );

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/register")
    public   Usuario register(@RequestBody Usuario usuario){
        return service.salvar(usuario);
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return service.listarUsuarios();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){

        boolean deletado = service.deletar(id);

        if(!deletado){
            return ResponseEntity.status(404).body("Usuario não encontrado");
        }
        return ResponseEntity.ok("Usuario deletado com Suceso");
    }
}
