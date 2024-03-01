package com.luisguilherme.motel.mapper.queryMotel.model;

public record QueryEntradaConsumo(
        Long id,
        Float total,
        Long idItem,
        Integer quantidade,
        Long idEntrada
) {
}
