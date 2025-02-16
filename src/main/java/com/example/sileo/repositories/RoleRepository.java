package com.example.sileo.repositories;

import com.example.sileo.domain.Roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Roles, UUID> {

    Optional<Roles> findByName(String name);
}
