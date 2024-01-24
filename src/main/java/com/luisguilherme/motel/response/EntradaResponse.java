package com.luisguilherme.motel.response;

import com.luisguilherme.motel.Enum.StatusEntrada;
import com.luisguilherme.motel.Enum.StatusPagamento;
import com.luisguilherme.motel.Enum.TipoPagamento;

import java.time.LocalDate;
import java.time.LocalTime;

public record EntradaResponse(Long id,
                              LocalDate dataRegistroEntrada,
                              LocalTime horaEntrada,
                              StatusEntrada statusEntrada,
                              TipoPagamento tipoPagamento,
                              String placa,
                              LocalTime horaSaida,
                              Long numeroQuarto,
                              StatusPagamento statusPagamento,
                              Float totalEntrada) {
}
