package com.example.sileo.domain.Grupo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class GrupoCreateDTO {
    @NotNull(message = "O campo código é obrigatório")
    public String codigo;
    @NotNull(message = "O campo taxa administração é obrigatório")
    @Min(value = 1, message = "O campo taxa administração deve ser maior que 0")
    public int admnistracaoPorcentagem;
    @NotNull(message = "O campo duração é obrigatório")
    @Min(value = 1, message = "O campo duração deve ser maior que 0")
    public int duracaoMeses;
}
