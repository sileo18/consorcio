package com.example.sileo.controller;

import com.example.sileo.domain.Grupo.Grupo;
import com.example.sileo.domain.Grupo.GrupoCreateDTO;
import com.example.sileo.services.GrupoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping
    @Operation(summary = "Create a new Grupo", description = "Create a new Grupo")
    public ResponseEntity<Grupo> create(@RequestBody  @Valid  GrupoCreateDTO grupoCreateDTO) {

        Grupo novoGrupo = grupoService.create(grupoCreateDTO);

        return ResponseEntity.ok(novoGrupo);
    }

    @GetMapping
    @Operation(summary = "Get all Grupos", description = "Get all Grupos")
    public ResponseEntity<List<Grupo>> getAll() {
        List<Grupo> grupos = grupoService.getAll();
        return ResponseEntity.ok(grupos);
    }

    @DeleteMapping
    @Operation(summary = "Delete a Grupo by id", description = "Delete a Grupo by id")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        grupoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
