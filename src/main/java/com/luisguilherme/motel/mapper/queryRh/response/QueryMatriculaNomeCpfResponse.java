package com.luisguilherme.motel.mapper.queryRh.response;

public record QueryMatriculaNomeCpfResponse(
        Integer codServidor,
        String matricula,
        String nome,
        String cpf
) {
}
