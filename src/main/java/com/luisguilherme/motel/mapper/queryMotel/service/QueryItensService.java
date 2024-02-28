package com.luisguilherme.motel.mapper.queryMotel.service;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryItens;
import com.luisguilherme.motel.mapper.queryMotel.repository.QueryItensRepository;
import org.springframework.stereotype.Service;

@Service
public class QueryItensService {

    private final QueryItensRepository queryItensRepository;

    public QueryItensService(QueryItensRepository queryItensRepository) {
        this.queryItensRepository = queryItensRepository;
    }

    public QueryItens criarItem(QueryItens queryItens) {
        queryItensRepository.criarItem(queryItens);

        return new QueryItens(
                queryItens.descricao(),
                queryItens.valor()
        );
    }
}
