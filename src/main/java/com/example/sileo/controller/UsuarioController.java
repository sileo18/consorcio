package com.example.sileo.controller;


import com.example.sileo.domain.Usuario.Usuario;
import com.example.sileo.domain.Usuario.UsuarioLoginDTO;
import com.example.sileo.domain.Usuario.UsuarioRegisterDTO;
import com.example.sileo.domain.Usuario.UsuarioUpdateDTO;
import com.example.sileo.services.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> register(@Valid @RequestBody UsuarioRegisterDTO usuarioRegisterDTO) {

        Usuario usuario = usuarioService.register(usuarioRegisterDTO);

        return ResponseEntity.ok(usuario);

    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> update(@Valid @RequestBody UsuarioUpdateDTO usuario,@PathVariable UUID id) {

         Usuario usuarioAtutalizado = usuarioService.update(id, usuario);
        return ResponseEntity.ok(usuarioAtutalizado);

    }
}
