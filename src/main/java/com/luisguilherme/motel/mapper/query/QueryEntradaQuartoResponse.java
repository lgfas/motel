package com.luisguilherme.motel.mapper.query;

import java.time.LocalDate;

public record QueryEntradaQuartoResponse(
        Long idEntrada,
        String placa,
        QuartoResponse quartoResponse
) {
    public record QuartoResponse(
            Long numeroQuarto,
            String descricaoQuarto,
            Integer capacidadePessoa
    ) {}
}
