package com.example.sileo.domain.Grupo;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class GrupoCreateDTO {
    @NotNull(message = "O campo nome é obrigatório")
    public String codigo;
    @NotNull(message = "O campo taxa administração é obrigatório")
    public int admnistracaoPorcentagem;
    @NotNull(message = "O campo duração é obrigatório")
    public int duracaoMeses;
}
