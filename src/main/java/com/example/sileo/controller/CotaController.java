package com.example.sileo.controller;

import com.example.sileo.domain.Cota.Cota;
import com.example.sileo.domain.Cota.CotaCreateDTO;
import com.example.sileo.domain.Cota.CotaGetDTO;
import com.example.sileo.services.CotaService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cotas")
public class CotaController {

    @Autowired
    private CotaService cotaService;

    @PostMapping
    public ResponseEntity<Cota> create(@Valid @RequestBody CotaCreateDTO cotaCreateDTO) {

        Cota cota = cotaService.create(cotaCreateDTO);

        return ResponseEntity.ok(cota);
    }

    @GetMapping("/{cotaId}")
    public ResponseEntity<CotaGetDTO> getCota(@PathVariable UUID cotaId) {
        CotaGetDTO cota = cotaService.getCota(cotaId);
        return ResponseEntity.ok(cota);
    }
}
