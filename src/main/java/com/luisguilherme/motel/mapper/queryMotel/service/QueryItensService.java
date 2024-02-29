package com.luisguilherme.motel.mapper.queryMotel.service;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryItens;
import com.luisguilherme.motel.mapper.queryMotel.repository.QueryItensRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class QueryItensService {

    private final QueryItensRepository queryItensRepository;

    public QueryItensService(QueryItensRepository queryItensRepository) {
        this.queryItensRepository = queryItensRepository;
    }

    public void criarItem(QueryItens queryItens) {
        queryItensRepository.criarItem(queryItens);
    }

    public Page<QueryItens> obterItens (Pageable pageable) {
        // Obter a página de itens do repositório
        Page<QueryItens> page = queryItensRepository.obterItens(pageable);

        // Criar uma nova lista com os dados da página
        List<QueryItens> itens = new ArrayList<>(page.getContent());

        // Ordenar os itens de acordo com o id ao contrário
        itens.sort(Comparator.comparing(QueryItens::id).reversed());

        // Criar uma nova página com os itens ordenados
        return new PageImpl<>(itens, pageable, page.getTotalElements());
    }

    public void atualizarItem (QueryItens queryItens) {
        queryItensRepository.atualizarItem(queryItens);
    }

    public void deletarItem (Long id) {
        queryItensRepository.deletarItem(id);
    }
}
