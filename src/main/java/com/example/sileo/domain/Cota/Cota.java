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

    @Column(nullable = false, length = 4)
    private String codigo;
    @Column(nullable = false)
    private double categoria;
    @Column(nullable = false)
    private int credito;
    @Column(nullable = false)
    private int planoMeses;
    @Column(nullable = false)
    private int totalPago;
    @Column(nullable = false)
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

   // public void setCategoria(double ) {
   //     this.categoria = calcularCategoria();
//}

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

   /* public void calcularCategoria() {
        this.categoria = this.credito + (this.credito * (this.grupo.getTaxadeAdm() / 200));
    }*/

    public void calcularParcela() {
        this.parcela = this.categoria / this.planoMeses;
    }
}
