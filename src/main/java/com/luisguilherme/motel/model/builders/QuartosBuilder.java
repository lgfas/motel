package com.luisguilherme.motel.model.builders;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.model.Quartos;

public class QuartosBuilder {

    private Long numero;
    private String descricao;
    private Long capacidadePessoa;
    private StatusDoQuarto statusDoQuarto;

    public QuartosBuilder numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public QuartosBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public QuartosBuilder capacidadePessoa(Long capacidadePessoa) {
        this.capacidadePessoa = capacidadePessoa;
        return this;
    }

    public QuartosBuilder statusDoQuarto(StatusDoQuarto statusDoQuarto) {
        this.statusDoQuarto = statusDoQuarto;
        return this;
    }

    public Quartos build() {
        Quartos quartos = new Quartos();

        quartos.setNumero(numero);
        quartos.setDescricao(descricao);
        quartos.setCapacidadePessoa(capacidadePessoa);
        quartos.setStatusDoQuarto(statusDoQuarto);

        return quartos;
    }
}
