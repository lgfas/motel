package com.luisguilherme.motel.mapper.queryMotel.controller;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntrada;
import com.luisguilherme.motel.mapper.queryMotel.service.QueryEntradaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queryEntradas")
public class QueryEntradaController {

    private final QueryEntradaService queryEntradaService;

    public QueryEntradaController(QueryEntradaService queryEntradaService) {
        this.queryEntradaService = queryEntradaService;
    }

    @PostMapping("/criarEntrada")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEntrada(QueryEntrada queryEntrada, Long idQuarto) {
        queryEntradaService.criarEntrada(queryEntrada, idQuarto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/obterEntradas")
    public Page<QueryEntrada> obterEntradas(Pageable pageable) {
        return queryEntradaService.obterEntradas(pageable);
    }

    @PutMapping("/atualizarEntrada")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarEntrada (Long id, QueryEntrada queryEntrada) {
        queryEntradaService.atualizarEntrada(id, queryEntrada);
    }

}
