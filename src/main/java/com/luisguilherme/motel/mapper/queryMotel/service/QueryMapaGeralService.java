package com.luisguilherme.motel.mapper.queryMotel.service;


import com.luisguilherme.motel.mapper.queryMotel.model.QueryMapaGeral;
import com.luisguilherme.motel.mapper.queryMotel.repository.QueryMapaGeralRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class QueryMapaGeralService {

    private final QueryMapaGeralRepository queryMapaGeralRepository;

    public QueryMapaGeralService(QueryMapaGeralRepository queryMapaGeralRepository) {
        this.queryMapaGeralRepository = queryMapaGeralRepository;
    }

    public void criarMapa(QueryMapaGeral queryMapaGeral) {

        queryMapaGeralRepository.criarMapa(queryMapaGeral);
    }

    public Page<QueryMapaGeral> obterMapas (Pageable pageable) {

        Page<QueryMapaGeral> page = queryMapaGeralRepository.obterMapas(pageable);

        List<QueryMapaGeral> mapas = new ArrayList<>(page.getContent());

        mapas.sort(Comparator.comparing(QueryMapaGeral::id).reversed());

        return new PageImpl<>(mapas, pageable, page.getTotalElements());
    }

    public void atualizarMapa (Long id, QueryMapaGeral mapaGeral) {

        queryMapaGeralRepository.atualizarMapa(id, mapaGeral);
    }
}
