package com.luisguilherme.motel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mt03_itens")
public class Itens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt03_codigo_itens")
    private Long id;
    @Column(name = "mt03_descricao")
    private String descricao;
    @Column(name = "mt03_valor")
    private Float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
