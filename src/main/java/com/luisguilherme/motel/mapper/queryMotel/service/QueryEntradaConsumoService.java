package com.luisguilherme.motel.mapper.queryMotel.service;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntradaConsumo;
import com.luisguilherme.motel.mapper.queryMotel.repository.QueryEntradaConsumoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class QueryEntradaConsumoService {

    private final QueryEntradaConsumoRepository queryEntradaConsumoRepository;

    public QueryEntradaConsumoService(QueryEntradaConsumoRepository queryEntradaConsumoRepository) {
        this.queryEntradaConsumoRepository = queryEntradaConsumoRepository;
    }

    public void criarEntradaConsumo (QueryEntradaConsumo queryEntradaConsumo, Long idItem, Long idEntrada) {
        queryEntradaConsumoRepository.criarEntradaConsumo(queryEntradaConsumo, idItem, idEntrada);
    }

    public Page<QueryEntradaConsumo> obterEntradasConsumo (Pageable pageable) {

        Page<QueryEntradaConsumo> page = queryEntradaConsumoRepository.obterEntradasConsumo(pageable);

        List<QueryEntradaConsumo> mapas = new ArrayList<>(page.getContent());

        mapas.sort(Comparator.comparing(QueryEntradaConsumo::id).reversed());

        return new PageImpl<>(mapas, pageable, page.getTotalElements());
    }

    public void deletarEntradaConsumo (Long id) {
        queryEntradaConsumoRepository.deletarEntradaConsumo(id);
    }
}
