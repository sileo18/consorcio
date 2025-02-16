package com.example.sileo.domain.UsuarioRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UsuarioRoleKey {
    @Column(name = "usuario_id")
    private UUID usuarioId;
    @Column(name = "role_id")
    private UUID roleId;

    public UsuarioRoleKey() {
    }

    public UsuarioRoleKey(UUID usuarioId, UUID roleId) {
        this.usuarioId = usuarioId;
        this.roleId = roleId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioRoleKey)) return false;
        UsuarioRoleKey that = (UsuarioRoleKey) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, roleId);
    }
}
