package com.example.sileo.domain.Grupo;

public class GrupoGetDTO {

    private String codigo;
    private int admnistracaoPorcentagem;
    private int duracaoMeses;

    public GrupoGetDTO(String codigo, int admnistracaoPorcentagem, int duracaoMeses) {
        this.codigo = codigo;
        this.admnistracaoPorcentagem = admnistracaoPorcentagem;
        this.duracaoMeses = duracaoMeses;
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

    public void setAdmnistracaoPorcentagem(int admnistracaoPorcentagem) {
        this.admnistracaoPorcentagem = admnistracaoPorcentagem;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }
}
