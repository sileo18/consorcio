package com.example.sileo.controller;

import com.example.sileo.domain.Cota.Cota;
import com.example.sileo.domain.Cota.CotaCreateDTO;
import com.example.sileo.domain.Cota.CotaGetDTO;
import com.example.sileo.services.CotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "Create a new Cota", description = "Create a new Cota")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cota> create(@Valid @RequestBody CotaCreateDTO cotaCreateDTO) {

        Cota cota = cotaService.create(cotaCreateDTO);

        return ResponseEntity.ok(cota);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all Cotas", description = "Get all Cotas")
    public ResponseEntity<List<Cota>> getCotas() {
        List<Cota> cotas = cotaService.getAllCotas();
        return ResponseEntity.ok(cotas);
    }

    @GetMapping("/{cotaId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get a Cota by id", description = "Get a Cota by id")
    public ResponseEntity<CotaGetDTO> getCota(@PathVariable UUID cotaId) {
        CotaGetDTO cota = cotaService.getCota(cotaId);
        return ResponseEntity.ok(cota);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get a Cota by codigo", description = "Get a Cota by codigo")
    public ResponseEntity<CotaGetDTO> getCota(@PathVariable String codigo) {
        CotaGetDTO cota = cotaService.getCotaByCodigo(codigo);
        return ResponseEntity.ok(cota);
    }

    @DeleteMapping("/{cotaId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a Cota by id", description = "Delete a Cota by id")
    public ResponseEntity<Void> deleteCota(@PathVariable UUID cotaId) {
        cotaService.delete(cotaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasRole('USER, ADMIN')")
    @Operation(summary = "Get all Cotas by Usuario ID", description = "Get all Cotas related to a specific Usuario")
    public ResponseEntity<List<Cota>> getCotasByUsuarioId(@PathVariable UUID usuarioId) {
        List<Cota> cotas = cotaService.getCotasByUsuarioId(usuarioId);

        if(cotas == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cotas);
    }

    @GetMapping("/grupo/{grupoId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all Cotas by Grupo ID", description = "Get all Cotas related to a specific Grupo")
    public ResponseEntity<List<Cota>> getCotasByGrupoId(@PathVariable UUID grupoId) {
        List<Cota> cotas = cotaService.getCotasByGrupoId(grupoId);

        if(cotas == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cotas);
    }

}
