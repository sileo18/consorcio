package com.example.sileo.controller;

import com.example.sileo.domain.Grupo.Grupo;
import com.example.sileo.domain.Grupo.GrupoCreateDTO;
import com.example.sileo.services.GrupoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping
    public ResponseEntity<Grupo> create(@RequestBody  @Valid  GrupoCreateDTO grupoCreateDTO) {

        Grupo novoGrupo = grupoService.create(grupoCreateDTO);

        return ResponseEntity.ok(novoGrupo);
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> getAll() {
        List<Grupo> grupos = grupoService.getAll();
        return ResponseEntity.ok(grupos);
    }
}
