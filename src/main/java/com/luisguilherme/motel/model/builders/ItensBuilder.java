package com.luisguilherme.motel.model.builders;

import com.luisguilherme.motel.model.Itens;

public class ItensBuilder {

    private String descricao;
    private Float valor;

    public ItensBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ItensBuilder valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public Itens build() {
        Itens itens = new Itens();

        itens.setDescricao(descricao);
        itens.setValor(valor);

        return itens;
    }
}
