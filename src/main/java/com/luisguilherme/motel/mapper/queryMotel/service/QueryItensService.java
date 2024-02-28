package com.luisguilherme.motel.mapper.queryMotel.service;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryItens;
import com.luisguilherme.motel.mapper.queryMotel.repository.QueryItensRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryItensService {

    private final QueryItensRepository queryItensRepository;

    public QueryItensService(QueryItensRepository queryItensRepository) {
        this.queryItensRepository = queryItensRepository;
    }

    public void criarItem(QueryItens queryItens) {
        queryItensRepository.criarItem(queryItens);
    }

    public Page<QueryItens> obterItens (Pageable pageable, int page, int size) {
        var retorno = queryItensRepository.obterItens(page, size).stream()
                .sorted(Comparator.comparing(QueryItens::id).reversed())
                .toList();

        return new PageImpl<>(retorno, pageable, size);
    }

    public void atualizarItem (QueryItens queryItens) {
        queryItensRepository.atualizarItem(queryItens);
    }

    public void deletarItem (Long id) {
        queryItensRepository.deletarItem(id);
    }
}
