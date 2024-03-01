package com.luisguilherme.motel.mapper.queryMotel.controller;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryQuartos;
import com.luisguilherme.motel.mapper.queryMotel.service.QueryQuartosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queryQuartos")
public class QueryQuartosController {

    private final QueryQuartosService queryQuartosService;

    public QueryQuartosController(QueryQuartosService queryQuartosService) {
        this.queryQuartosService = queryQuartosService;
    }

    @PostMapping("/criarQuarto")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarQuarto(QueryQuartos queryQuartos, StatusDoQuarto statusDoQuarto) {
        queryQuartosService.criarQuarto(queryQuartos, statusDoQuarto);
    }

    @GetMapping("/obterQuartos")
    @ResponseStatus(HttpStatus.OK)
    public Page<QueryQuartos> obterQuartos (Pageable pageable) {
        return queryQuartosService.obterQuartos(pageable);
    }
}
