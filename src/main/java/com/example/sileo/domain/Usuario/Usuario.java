package com.example.sileo.domain.Usuario;

import com.example.sileo.domain.Cota.Cota;
import com.example.sileo.domain.Usuario_roles.UsuarioRoles;
import com.example.sileo.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Table(name = "usuario")
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<Cota> cotas;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UsuarioRoles> roles;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<UsuarioRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UsuarioRoles> roles) {
        this.roles = roles;
    }



}
