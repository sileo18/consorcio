package com.example.sileo.repositories;

import com.example.sileo.domain.Cota.Cota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CotaRepository extends JpaRepository<Cota, UUID> {
}
