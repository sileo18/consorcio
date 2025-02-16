package com.example.sileo.controller;

import com.example.sileo.domain.Usuario.*;
import com.example.sileo.repositories.UsuarioRepository;
import com.example.sileo.security.TokenService;
import com.example.sileo.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    @Operation(summary = "Auth the user", description = "Auth the user")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginDTO) {

        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/register")
    @Operation(summary = "Create a new Usuario", description = "Create a new Usuario")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO usuario) {

            RegisterResponseDTO novoUsuario = authService.registerUser(usuario);

            return ResponseEntity.ok(novoUsuario);

    }

    @PostMapping("/admin/register")
    @Operation(summary = "Register a new admin", description = "Create a new user with role ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponseDTO> registerAdmin(@Valid @RequestBody RegisterRequestDTO usuario) {

        RegisterResponseDTO novoUsuario = authService.registerAdmin(usuario);

        return ResponseEntity.ok(novoUsuario);
    }

}
