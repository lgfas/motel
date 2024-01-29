package com.luisguilherme.motel.model.builders;

import com.luisguilherme.motel.model.EntradaConsumo;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Itens;

public class EntradaConsumoBuilder {

    private Float total;
    private Itens itens;
    private Integer quantidade;
    private Entradas entradas;

    public EntradaConsumoBuilder total(Float total) {
        this.total = total;
        return this;
    }

    public EntradaConsumoBuilder itens(Itens itens) {
        this.itens = itens;
        return this;
    }

    public EntradaConsumoBuilder quantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public EntradaConsumoBuilder entradas(Entradas entradas) {
        this.entradas = entradas;
        return this;
    }

    public EntradaConsumo build() {
        EntradaConsumo entradaConsumo = new EntradaConsumo();

        entradaConsumo.setTotal(total);
        entradaConsumo.setItens(itens);
        entradaConsumo.setQuantidade(quantidade);
        entradaConsumo.setEntradas(entradas);

        return entradaConsumo;
    }

}
