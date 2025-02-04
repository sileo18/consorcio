package com.example.sileo.domain.Cota;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CotaCreateDTO {

    @NotNull(message = "O campo código é obrigatório")
    private String codigo;

    @NotNull(message = "O campo crédito é obrigatório")
    private int credito;

    @NotNull(message = "O campo usuário é obrigatório")
    private UUID usuarioId;

    @NotNull(message = "O campo grupo é obrigatório")
    private UUID grupoId;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(UUID grupoId) {
        this.grupoId = grupoId;
    }
}
