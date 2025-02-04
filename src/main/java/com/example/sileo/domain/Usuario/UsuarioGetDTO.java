package com.example.sileo.domain.Usuario;

public class UsuarioGetDTO {

    private String nome;

    public UsuarioGetDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
