package com.luisguilherme.motel.model.builders;

import com.luisguilherme.motel.Enum.StatusEntrada;
import com.luisguilherme.motel.Enum.StatusPagamento;
import com.luisguilherme.motel.Enum.TipoPagamento;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Quartos;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntradaBuilder {

    private LocalDate dataRegistroEntrada;
    private StatusEntrada statusEntrada;
    private TipoPagamento tipoPagamento;
    private String placa;
    private LocalTime horaSaida;
    private Quartos quartos;
    private StatusPagamento statusPagamento;
    private Float totalEntrada;
    private LocalTime horaEntrada;

    public EntradaBuilder dataRegistroEntrada(LocalDate dataRegistroEntrada) {
        this.dataRegistroEntrada = dataRegistroEntrada;
        return this;
    }

    public EntradaBuilder statusEntrada(StatusEntrada statusEntrada) {
        this.statusEntrada = statusEntrada;
        return this;
    }

    public EntradaBuilder tipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
        return this;
    }

    public EntradaBuilder placa(String placa) {
        this.placa = placa;
        return this;
    }

    public EntradaBuilder horaSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
        return this;
    }

    public EntradaBuilder quartos(Quartos quartos) {
        this.quartos = quartos;
        return this;
    }

    public EntradaBuilder statusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
        return this;
    }

    public EntradaBuilder totalEntrada(Float totalEntrada) {
        this.totalEntrada = totalEntrada;
        return this;
    }

    public EntradaBuilder horaEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
        return this;
    }

    public Entradas build() {
        Entradas entradas = new Entradas();
        entradas.setDataRegistroEntrada(dataRegistroEntrada);
        entradas.setStatusEntrada(statusEntrada);
        entradas.setTipoPagamento(tipoPagamento);
        entradas.setPlaca(placa);
        entradas.setHoraSaida(horaSaida);
        entradas.setQuartos(quartos);
        entradas.setStatusPagamento(statusPagamento);
        entradas.setTotalEntrada(totalEntrada);
        entradas.setHoraEntrada(horaEntrada);

        return entradas;
    }
}
