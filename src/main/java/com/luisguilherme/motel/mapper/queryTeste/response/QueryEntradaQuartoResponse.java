package com.luisguilherme.motel.mapper.queryTeste.response;

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
