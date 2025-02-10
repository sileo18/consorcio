package com.example.sileo.domain.Usuario;

public class RegisterResponseDTO {
    private String token;
    private String nome;

    public RegisterResponseDTO(String token, String nome) {
        this.token = token;
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public String getNome() {
        return nome;
    }
}
