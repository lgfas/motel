package com.luisguilherme.motel.mapper.queryMotel.response;

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
