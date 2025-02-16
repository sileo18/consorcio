package com.example.sileo.domain.UsuarioRole;


import com.example.sileo.domain.Roles.Roles;
import com.example.sileo.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_roles")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRole {

    @EmbeddedId
    private UsuarioRoleKey id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Roles role;

    public UsuarioRoleKey getId() {
        return id;
    }

    public void setId(UsuarioRoleKey id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}


