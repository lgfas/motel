package com.luisguilherme.motel.mapper.queryMotel.model;

public record QueryEntrada(
        Long id,
        String dataRegistroEntrada,
        String statusEntrada,
        String tipoPagamento,
        String placa,
        String horaSaida,
        Long idQuarto,
        String statusPagamento,
        Float totalEntrada,
        String horaEntrada
) {
}
