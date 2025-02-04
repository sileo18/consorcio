package com.example.sileo.domain.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRegisterDTO {
    @NotNull(message = "O campo cpf é obrigatório")
    @Size(max = 11, message = "O campo cpf deve ter no máximo 11 caracteres")
    @Min(value = 11, message = "O campo cpf deve ter no mínimo 11 caracteres")
    public String cpf;
    @NotNull(message = "O campo nome é obrigatório")
    @Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres")
    @Min(value = 1, message = "O campo cpf deve ter no mínimo 11 caracteres")
    private String nome;
    @NotNull(message = "O campo email é obrigatório")
    @Email(message = "O campo email deve ser um email válido")
    private String email;
    @NotNull(message = "O campo senha é obrigatório")
    private String senha;

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
}
