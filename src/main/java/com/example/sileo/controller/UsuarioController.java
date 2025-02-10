package com.example.sileo.controller;


import com.example.sileo.domain.Usuario.Usuario;
import com.example.sileo.domain.Usuario.UsuarioUpdateDTO;
import com.example.sileo.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping
    @Operation(summary = "Get all Usuarios", description = "Get all Usuarios")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a Usuario by id", description = "Update a Usuario by id")
    public ResponseEntity<Usuario> update(@Valid @RequestBody UsuarioUpdateDTO usuario,@PathVariable UUID id) {

        Usuario usuarioAtutalizado = usuarioService.update(id, usuario);

        if (usuarioAtutalizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarioAtutalizado);

    }


}
