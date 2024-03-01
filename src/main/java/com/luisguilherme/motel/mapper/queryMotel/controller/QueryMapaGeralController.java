package com.luisguilherme.motel.mapper.queryMotel.controller;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryMapaGeral;
import com.luisguilherme.motel.mapper.queryMotel.service.QueryMapaGeralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queryMapas")
public class QueryMapaGeralController {

    private final QueryMapaGeralService queryMapaGeralService;

    public QueryMapaGeralController(QueryMapaGeralService queryMapaGeralService) {
        this.queryMapaGeralService = queryMapaGeralService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/criarMapaGeral")
    public void criarMapa(QueryMapaGeral queryMapaGeral) {

        queryMapaGeralService.criarMapa(queryMapaGeral);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/obterMapas")
    public Page<QueryMapaGeral> obterMapas(Pageable pageable) {
        return queryMapaGeralService.obterMapas(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/atualizarMapaGeral/{id}")
    public void atualizarMapa(@PathVariable Long id, QueryMapaGeral mapaGeral) {

        queryMapaGeralService.atualizarMapa(id, mapaGeral);
    }

}
