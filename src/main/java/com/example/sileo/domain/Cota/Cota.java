package com.example.sileo.domain.Cota;

import com.example.sileo.domain.Grupo.Grupo;
import com.example.sileo.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Cota {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "codigo", nullable = false, length = 4)
    private String codigo;

    @Column(name = "categoria", nullable = false)
    private double categoria;

    @Column(name = "credito", nullable = false)
    private int credito;

    @Column(name = "planomeses", nullable = false)
    private int planoMeses;

    @Column(name = "totalpago",nullable = false)
    private double totalPago;

    @Column(name = "parcela", nullable = false)
    private double parcela;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") // FK Key
    private Usuario usuario;

    //Cota n ---- 1 Usuario
    // Many to one
    //Many quotas can have one user

    @ManyToOne
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    private Grupo grupo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCategoria() {
        return categoria;
    }

    public void setCategoria(int credito ) {
        int administracao = this.grupo.getAdmnistracaoPorcentagem();
        this.categoria = credito + (credito * administracao / 100);
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

    public void setPlanoMeses() {
        this.planoMeses = this.grupo.getDuracaoMeses();
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago() {
        this.totalPago = categoria;
    }

    public double getParcela() {
        return parcela;
    }

    public void setParcela() {
        this.parcela = categoria / this.planoMeses;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }


}
