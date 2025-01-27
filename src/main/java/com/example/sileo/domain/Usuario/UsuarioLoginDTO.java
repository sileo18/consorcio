package com.example.sileo.domain.Usuario;

import jakarta.validation.constraints.NotNull;

public class UsuarioLoginDTO {
    @NotNull(message = "O campo email é obrigatório")
    public String email;
    @NotNull(message = "O campo senha é obrigatório")
    public String senha;

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
}
