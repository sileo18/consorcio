package com.example.sileo.controller;


import com.example.sileo.domain.Usuario.Usuario;
import com.example.sileo.domain.Usuario.UsuarioRegisterDTO;
import com.example.sileo.domain.Usuario.UsuarioUpdateDTO;
import com.example.sileo.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        if (usuarioAtutalizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarioAtutalizado);

    }
}
