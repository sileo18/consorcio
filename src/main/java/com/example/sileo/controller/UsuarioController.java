package com.example.sileo.controller;


import com.example.sileo.domain.Usuario.Usuario;
import com.example.sileo.domain.Usuario.UsuarioRegisterDTO;
import com.example.sileo.services.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthencticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<Usuario> register(@Valid @RequestBody UsuarioRegisterDTO usuarioRegisterDTO) {

        Usuario usuario = usuarioService.register(usuarioRegisterDTO);

        return ResponseEntity.ok(usuario);

    }

    @PostMapping("/login")
    public ResponseEntity<Null> login(@Valid @RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha()));

        return ResponseEntity.ok(null);
    }
}
