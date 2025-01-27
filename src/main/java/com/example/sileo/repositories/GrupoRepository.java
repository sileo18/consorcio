package com.example.sileo.repositories;

import com.example.sileo.domain.Grupo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GrupoRepository extends JpaRepository<Grupo, UUID> {
}
