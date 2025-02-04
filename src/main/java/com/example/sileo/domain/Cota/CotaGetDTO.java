package com.example.sileo.domain.Cota;
import com.example.sileo.domain.Grupo.GrupoGetDTO;
import com.example.sileo.domain.Usuario.UsuarioGetDTO;
import jakarta.persistence.Column;

public class CotaGetDTO {

    private String codigo;
    private int credito;
    private int planoMeses;
    private double totalPago;
    private double parcela;

    private double categoria;
    private GrupoGetDTO grupo;
    private UsuarioGetDTO usuario;

    public double getCategoria() {
        return categoria;
    }

    public CotaGetDTO(String codigo, int credito, int planoMeses, double totalPago, double parcela, double categoria, GrupoGetDTO grupo, UsuarioGetDTO usuario) {
        this.codigo = codigo;
        this.credito = credito;
        this.planoMeses = planoMeses;
        this.totalPago = totalPago;
        this.parcela = parcela;
        this.grupo = grupo;
        this.usuario = usuario;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCredito() {
        return credito;
    }

    public int getPlanoMeses() {
        return planoMeses;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public double getParcela() {
        return parcela;
    }

    public GrupoGetDTO getGrupo() {
        return grupo;
    }

    public UsuarioGetDTO getUsuario() {
        return usuario;
    }
}