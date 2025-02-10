package com.example.sileo.domain.Usuario;

public class LoginResponseDTO {
    private String token;

    private String name;

    public String getName() {
        return name;
    }

    public LoginResponseDTO(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }
}
