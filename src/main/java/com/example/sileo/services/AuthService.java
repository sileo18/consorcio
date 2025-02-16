package com.example.sileo.services;

import com.example.sileo.domain.Roles.Roles;
import com.example.sileo.domain.Usuario.*;
import com.example.sileo.enums.UserRole;
import com.example.sileo.exeception.GlobalExceptionHandler;
import com.example.sileo.repositories.RoleRepository;
import com.example.sileo.repositories.UsuarioRepository;
import com.example.sileo.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

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
    public RegisterResponseDTO registerUser(@Valid RegisterRequestDTO registerDTO) {
        var usuarioExistente = usuarioRepository.findByEmail(registerDTO.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(registerDTO.getEmail());
        novoUsuario.setNome(registerDTO.getNome());
        novoUsuario.setCpf(registerDTO.getCpf());
        novoUsuario.setSenha(passwordEncoder.encode(registerDTO.getSenha()));

        // Corrigido para usar Optional
        Roles role = roleRepository.findByName(UserRole.ROLE_USER.name())
                .orElseThrow(() -> new RuntimeException("Role não encontrada: " + UserRole.ROLE_USER.name()));

        novoUsuario.setRoles(Set.of(role));

        usuarioRepository.save(novoUsuario);

        String token = tokenService.generateToken(novoUsuario);

        return new RegisterResponseDTO(token, novoUsuario.getNome());
    }

    @Transactional
    public RegisterResponseDTO registerAdmin(@Valid RegisterRequestDTO registerDTO) {
        var usuarioExistente = usuarioRepository.findByEmail(registerDTO.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(registerDTO.getEmail());
        novoUsuario.setNome(registerDTO.getNome());
        novoUsuario.setCpf(registerDTO.getCpf());
        novoUsuario.setSenha(passwordEncoder.encode(registerDTO.getSenha()));

        Roles role = roleRepository.findByName(UserRole.ROLE_ADMIN.name())
                .orElseThrow(() -> new RuntimeException("Role não encontrada: " + UserRole.ROLE_ADMIN.name()));

        novoUsuario.setRoles(Set.of(role));

        usuarioRepository.save(novoUsuario);

        String token = tokenService.generateToken(novoUsuario);

        return new RegisterResponseDTO(token, novoUsuario.getNome());
    }
}
