package com.example.sileo.services;

import com.example.sileo.domain.Usuario.*;
import com.example.sileo.repositories.UsuarioRepository;
import com.example.sileo.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginDTO) {

        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            String token = tokenService.generateToken(usuario);
            return new LoginResponseDTO(token, usuario.getNome());
        }

        throw new RuntimeException("Senha inválida");
    }

    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO registerDTO) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(registerDTO.getEmail());

        if(usuario.isEmpty()) {

            Usuario novoUsuario = new Usuario();

            novoUsuario.setEmail(registerDTO.getEmail());
            novoUsuario.setSenha(passwordEncoder.encode(registerDTO.getSenha()));
            novoUsuario.setNome(registerDTO.getNome());

            usuarioRepository.save(novoUsuario);

            String token = tokenService.generateToken(novoUsuario);

            return new RegisterResponseDTO(token, novoUsuario.getNome());
        }

        return null;
    }
}
