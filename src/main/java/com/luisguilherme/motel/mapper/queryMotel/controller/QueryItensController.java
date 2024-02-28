package com.luisguilherme.motel.mapper.queryMotel.controller;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryItens;
import com.luisguilherme.motel.mapper.queryMotel.service.QueryItensService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queryItens")
public class QueryItensController {

    private final QueryItensService queryItensService;

    public QueryItensController(QueryItensService queryItensService) {
        this.queryItensService = queryItensService;
    }

    @PostMapping("/criarItem")
    @ResponseStatus(HttpStatus.CREATED)
    public QueryItens criarItem(QueryItens queryItens) {
        return queryItensService.criarItem(queryItens);
    }
}
