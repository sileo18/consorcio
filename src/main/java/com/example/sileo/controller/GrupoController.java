package com.example.sileo.controller;

import com.example.sileo.domain.Grupo.Grupo;
import com.example.sileo.domain.Grupo.GrupoCreateDTO;
import com.example.sileo.services.GrupoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    public ResponseEntity<Grupo> create(GrupoCreateDTO grupoCreateDTO) {

        Grupo novoGrupo = grupoService.create(grupoCreateDTO);

        return ResponseEntity.ok(novoGrupo);
    }
}
