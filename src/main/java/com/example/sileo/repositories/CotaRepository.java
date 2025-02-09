package com.example.sileo.repositories;

import com.example.sileo.domain.Cota.Cota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CotaRepository extends JpaRepository<Cota, UUID> {
    Optional<Cota> findByCodigo(String codigo);

    List<Cota> findByUsuarioId(UUID usuarioId);

    List<Cota> findByGrupoId(UUID grupoId);
}
