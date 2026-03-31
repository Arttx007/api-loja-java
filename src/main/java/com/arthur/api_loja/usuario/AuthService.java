package com.arthur.api_loja.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

        private  final UsuarioRepository repository;
        private  final BCryptPasswordEncoder encoder;

        public AuthService(UsuarioRepository repository, BCryptPasswordEncoder encoder) {
            this.repository = repository;
            this.encoder = encoder;
        }
        public Usuario salvar(Usuario usuario){
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            return  repository.save(usuario);
        }

        public Usuario login(LoginDTO dto){

            Usuario usuario = repository.findByEmail(dto.getEmail()).orElse(null);

            if (usuario == null) {
                return null;
            }

            if (!encoder.matches(dto.getSenha(), usuario.getSenha())){
                return null;
            }


            return usuario;
        }
        public List<Usuario> listarUsuarios(){
            return repository.findAll();
        }
        public boolean deletar(Long id){

            Usuario usuario = repository.findById(id).orElse(null);

            if (usuario == null){
                return false;
            }

            repository.deleteById(id);
            return true;
        }
        public Usuario atualizar(Long id, Usuario dadosAtualizados){
            Usuario usuario = repository.findById(id).orElse(null);

            if (usuario == null) {
                return null;
            }

            usuario.setEmail(dadosAtualizados.getEmail());
            usuario.setRole(dadosAtualizados.getRole());

            if (dadosAtualizados.getSenha() != null && !dadosAtualizados.getSenha().isBlank()) {
                usuario.setSenha(encoder.encode(dadosAtualizados.getSenha()));
            }

            return repository.save(usuario);
        }
    }
