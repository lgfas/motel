package com.luisguilherme.motel.mapper.queryTeste.model;

import org.springframework.stereotype.Component;

@Component
public class QueryEntradaQuarto {

    public String queryEntradaQuarto(Long id) {
        StringBuilder query = new StringBuilder();

        query.append(
                """
                     SELECT mt01.mt01_codigo_entradas as idEntrada,
                            mt01.mt01_placa as placa,
                            mt02.mt02_numero as numeroQuarto,
                            mt02.mt02_descricao as descricaoQuarto,
                            mt02.mt02_capacidade_pessoa as capacidadePessoa
                     FROM mt01_entradas mt01
                     LEFT JOIN public.mt02_quartos mt02 on mt02.mt02_codigo_quartos = mt01.fkmt01mt02_codigo_quartos
                     WHERE mt01_codigo_entradas = """).append(id);

        return query.toString();
    }
}
