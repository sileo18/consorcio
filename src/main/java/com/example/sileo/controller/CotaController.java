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

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<Cota>> getCotas() {
        List<Cota> cotas = cotaService.getAllCotas();
        return ResponseEntity.ok(cotas);
    }

    @GetMapping("/{cotaId}")
    public ResponseEntity<CotaGetDTO> getCota(@PathVariable UUID cotaId) {
        CotaGetDTO cota = cotaService.getCota(cotaId);
        return ResponseEntity.ok(cota);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CotaGetDTO> getCota(@PathVariable String codigo) {
        CotaGetDTO cota = cotaService.getCotaByCodigo(codigo);
        return ResponseEntity.ok(cota);
    }

    @DeleteMapping("/{cotaId}")
    public ResponseEntity<Void> deleteCota(@PathVariable UUID cotaId) {
        cotaService.delete(cotaId);
        return ResponseEntity.noContent().build();
    }
}
