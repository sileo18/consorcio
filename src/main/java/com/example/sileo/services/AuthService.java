package com.example.sileo.services;

import com.example.sileo.domain.Usuario.*;
import com.example.sileo.domain.Usuario_roles.UsuarioRoles;
import com.example.sileo.enums.UserRole;
import com.example.sileo.repositories.UsuarioRepository;
import com.example.sileo.repositories.UsuarioRolesRepository;
import com.example.sileo.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Optional;
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
    public RegisterResponseDTO register(@RequestBody @Valid RegisterRequestDTO registerDTO) {

        var usuario = usuarioRepository.findByEmail(registerDTO.getEmail());

        if(usuario.isEmpty()) {

            Usuario novoUsuario = new Usuario();

            novoUsuario.setEmail(registerDTO.getEmail());
            novoUsuario.setNome(registerDTO.getNome());
            novoUsuario.setCpf(registerDTO.getCpf());
            novoUsuario.setSenha(passwordEncoder.encode(registerDTO.getSenha()));
            novoUsuario.setRoles(Set.of(usuarioRoleRepository.findByName(UserRole.USER.name())));

            usuarioRepository.save(novoUsuario);

            String token = tokenService.generateToken(novoUsuario);

            return new RegisterResponseDTO(token, novoUsuario.getNome());
        }

        throw new RuntimeException("Usuário já cadastrado");
    }
}
