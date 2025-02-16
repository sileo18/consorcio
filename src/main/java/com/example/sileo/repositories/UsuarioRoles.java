package com.example.sileo.repositories;

import com.example.sileo.domain.UsuarioRole.UsuarioRole;
import com.example.sileo.domain.UsuarioRole.UsuarioRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsuarioRoles extends JpaRepository<UsuarioRole, UsuarioRoleKey> {
    List<UsuarioRole> findByUsuarioId(UUID usuarioId);
    List<UsuarioRole> findByRoleId(UUID roleId);
    void deleteByUsuarioId(UUID usuarioId);
    void deleteByRoleId(UUID roleId);
}
