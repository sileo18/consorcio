package com.example.sileo.repositories;

;
import com.example.sileo.domain.Usuario_roles.UsuarioRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolesRepository extends JpaRepository<UsuarioRoles, Long> {
    UsuarioRoles findByName(String name);
}
