package com.example.sileo.domain.Cota;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CotaCreateDTO {

    @NotNull(message = "O campo código é obrigatório")
    private String codigo;

    @NotNull(message = "O campo categoria é obrigatório")
    private double categoria;

    @NotNull(message = "O campo crédito é obrigatório")
    private int credito;

    @NotNull(message = "O campo plano de meses é obrigatório")
    private int planoMeses;

    @NotNull(message = "O campo total pago é obrigatório")
    private int totalPago;

    @NotNull(message = "O campo parcela é obrigatório")
    private double parcela;

    @NotNull(message = "O campo usuário é obrigatório")
    private UUID usuarioId;

    @NotNull(message = "O campo grupo é obrigatório")
    private UUID grupoId;
}
