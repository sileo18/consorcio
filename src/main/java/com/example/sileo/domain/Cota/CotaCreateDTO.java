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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCategoria() {
        return categoria;
    }

    public void setCategoria(double categoria) {
        this.categoria = categoria;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getPlanoMeses() {
        return planoMeses;
    }

    public void setPlanoMeses(int planoMeses) {
        this.planoMeses = planoMeses;
    }

    public int getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(int totalPago) {
        this.totalPago = totalPago;
    }

    public double getParcela() {
        return parcela;
    }

    public void setParcela(double parcela) {
        this.parcela = parcela;
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
