package com.example.sileo.domain.Grupo;

import com.example.sileo.domain.Cota.Cota;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Table(name = "grupo")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Grupo {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "codigo", nullable = false, length = 4)
    private String codigo;

    @Column(name = "admnistracaoporcentagem", nullable = false)
    private int admnistracaoPorcentagem;

    @Column(name = "duracaomeses", nullable = false)
    private int duracaoMeses;

    @OneToMany(mappedBy = "grupo")
    private List<Cota> cotas;

    // Getters and setters
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

    public int getAdmnistracaoPorcentagem() {
        return admnistracaoPorcentagem;
    }

    public void setadmnistracaoPorcentagem(int admnistracaoPorcentagem) {
        this.admnistracaoPorcentagem = admnistracaoPorcentagem;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }
}