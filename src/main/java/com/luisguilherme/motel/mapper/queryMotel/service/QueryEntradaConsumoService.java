package com.luisguilherme.motel.mapper.queryMotel.service;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntradaConsumo;
import com.luisguilherme.motel.mapper.queryMotel.repository.QueryEntradaConsumoRepository;
import org.springframework.stereotype.Service;

@Service
public class QueryEntradaConsumoService {

    private final QueryEntradaConsumoRepository queryEntradaConsumoRepository;

    public QueryEntradaConsumoService(QueryEntradaConsumoRepository queryEntradaConsumoRepository) {
        this.queryEntradaConsumoRepository = queryEntradaConsumoRepository;
    }

    public void criarEntradaConsumo (QueryEntradaConsumo queryEntradaConsumo, Long idItem, Long idEntrada) {
        queryEntradaConsumoRepository.criarEntradaConsumo(queryEntradaConsumo, idItem, idEntrada);
    }
}
