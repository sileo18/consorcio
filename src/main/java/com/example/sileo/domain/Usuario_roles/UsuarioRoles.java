package com.example.sileo.domain.Usuario_roles;

import com.example.sileo.enums.UserRole;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario_roles")
public class UsuarioRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Values {

        ADMIN(1L),
        USER(2L);

        long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }
    }
}
