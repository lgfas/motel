package com.luisguilherme.motel.mapper.queryMotel.service;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryQuartos;
import com.luisguilherme.motel.mapper.queryMotel.repository.QueryQuartosRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class QueryQuartosService {

    private final QueryQuartosRepository queryQuartosRepository;

    public QueryQuartosService(QueryQuartosRepository queryQuartosRepository) {
        this.queryQuartosRepository = queryQuartosRepository;
    }

    public void criarQuarto(QueryQuartos queryQuartos) {
        queryQuartosRepository.criarQuarto(queryQuartos);
    }

    public Page<QueryQuartos> obterQuartos (Pageable pageable) {

        Page<QueryQuartos> page = queryQuartosRepository.obterQuartos(pageable);

        List<QueryQuartos> quartos = new ArrayList<>(page.getContent());

        quartos.sort(Comparator.comparing(QueryQuartos::id).reversed());

        return new PageImpl<>(quartos, pageable, page.getTotalElements());
    }
}
