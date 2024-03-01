package com.luisguilherme.motel.mapper.queryMotel.service;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntrada;
import com.luisguilherme.motel.mapper.queryMotel.repository.QueryEntradaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class QueryEntradaService {

    private final QueryEntradaRepository queryEntradaRepository;

    public QueryEntradaService(QueryEntradaRepository queryEntradaRepository) {
        this.queryEntradaRepository = queryEntradaRepository;
    }

    public void criarEntrada(QueryEntrada queryEntrada, Long idQuarto) {
        queryEntradaRepository.criarEntrada(queryEntrada,idQuarto);
    }

    public Page<QueryEntrada> obterEntradas (Pageable pageable) {

        Page<QueryEntrada> page = queryEntradaRepository.obterEntradas(pageable);

        List<QueryEntrada> mapas = new ArrayList<>(page.getContent());

        mapas.sort(Comparator.comparing(QueryEntrada::id).reversed());

        return new PageImpl<>(mapas, pageable, page.getTotalElements());
    }

    public void atualizarEntrada (Long id, QueryEntrada queryEntrada) {
        queryEntradaRepository.atualizarEntrada(id, queryEntrada);
    }
}
