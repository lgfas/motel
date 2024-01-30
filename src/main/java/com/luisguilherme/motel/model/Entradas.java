package com.luisguilherme.motel.model;

import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.StatusPagamento;
import com.luisguilherme.motel.enums.TipoPagamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "mt01_entradas")
public class Entradas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt01_codigo_entradas")
    private Long id;

    @Column(name = "mt01_data_registro_entrada")
    private LocalDate dataRegistroEntrada;

    @Column(name = "mt01_status_entrada")
    @Enumerated(EnumType.STRING)
    private StatusEntrada statusEntrada;

    @Column(name = "mt01_tipo_pagamento")
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @Column(name = "mt01_placa")
    private String placa;

    @Column(name = "mt01_hora_saida")
    private LocalTime horaSaida;
    @ManyToOne
    @JoinColumn(name = "fkmt01mt02_codigo_quartos")
    private Quartos quartos;

    @Column(name = "mt01_status_pagamento")
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Column(name = "mt01_total_entrada")
    private Float totalEntrada;

    @Column(name = "mt01_hora_entrada")
    private LocalTime horaEntrada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataRegistroEntrada() {
        return dataRegistroEntrada;
    }

    public void setDataRegistroEntrada(LocalDate dataRegistroEntrada) {
        this.dataRegistroEntrada = dataRegistroEntrada;
    }

    public StatusEntrada getStatusEntrada() {
        return statusEntrada;
    }

    public void setStatusEntrada(StatusEntrada statusEntrada) {
        this.statusEntrada = statusEntrada;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Quartos getQuartos() {
        return quartos;
    }

    public void setQuartos(Quartos quartos) {
        this.quartos = quartos;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Float getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(Float totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Entradas(LocalDate dataRegistroEntrada, StatusEntrada statusEntrada, TipoPagamento tipoPagamento, String placa, LocalTime horaSaida, Quartos quartos, StatusPagamento statusPagamento, Float totalEntrada, LocalTime horaEntrada) {
        this.dataRegistroEntrada = dataRegistroEntrada;
        this.statusEntrada = statusEntrada;
        this.tipoPagamento = tipoPagamento;
        this.placa = placa;
        this.horaSaida = horaSaida;
        this.quartos = quartos;
        this.statusPagamento = statusPagamento;
        this.totalEntrada = totalEntrada;
        this.horaEntrada = horaEntrada;
    }

    public Entradas() {
    }
}
