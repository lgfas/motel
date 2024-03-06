package com.luisguilherme.motel.mapper.queryRh.response;

public record QueryServidorRelatorio(
        Integer codPessoa,
        Integer codServidor,
        String nome,
        String dataDeNascimento,
        String descricaoSexo,
        String nomeMae,
        String nomePai,
        String rg,
        String cpf
) {
}
