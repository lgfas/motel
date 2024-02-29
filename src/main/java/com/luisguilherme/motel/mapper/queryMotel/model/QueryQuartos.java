package com.luisguilherme.motel.mapper.queryMotel.model;

import com.luisguilherme.motel.enums.StatusDoQuarto;

public record QueryQuartos(
        Long id,
        Long numero,
        String descricao,
        Long capacidadePessoa,
        String statusDoQuarto
) {
}
