package com.example.sileo.services;

import com.example.sileo.domain.Usuario.*;
import com.example.sileo.domain.Usuario_roles.UsuarioRoles;
import com.example.sileo.enums.UserRole;
import com.example.sileo.repositories.UsuarioRepository;
import com.example.sileo.repositories.UsuarioRolesRepository;
import com.example.sileo.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRolesRepository usuarioRoleRepository;

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

            System.out.println("Registrando usuário: " + registerDTO);

            Usuario novoUsuario = new Usuario();

            novoUsuario.setEmail(registerDTO.getEmail());
            novoUsuario.setNome(registerDTO.getNome());
            novoUsuario.setCpf(registerDTO.getCpf());
            novoUsuario.setSenha(passwordEncoder.encode(registerDTO.getSenha()));

            usuarioRepository.save(novoUsuario);

            for (UserRole role : registerDTO.getRole()) {
                UsuarioRoles usuarioRole = new UsuarioRoles();
                usuarioRole.setUsuarioId(novoUsuario.getId());
                usuarioRole.setRole(role.getRole());
                usuarioRoleRepository.save(usuarioRole);
            }

            String token = tokenService.generateToken(novoUsuario);

            return new RegisterResponseDTO(token, novoUsuario.getNome());
        }

        return null;
    }
}
