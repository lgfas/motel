package com.luisguilherme.motel.mapper.queryMotel.model;

public record QueryMapaGeral(
        Long id,
        Integer apartment,
        Float entrada,
        String report,
        Float saida,
        Float total,
        String hora,
        String data
) {
}
