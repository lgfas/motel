package com.luisguilherme.motel.mapper.queryMotel.controller;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntradaConsumo;
import com.luisguilherme.motel.mapper.queryMotel.service.QueryEntradaConsumoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queryEntradaConsumo")
public class QueryEntradaConsumoController {

    private final QueryEntradaConsumoService queryEntradaConsumoService;

    public QueryEntradaConsumoController(QueryEntradaConsumoService queryEntradaConsumoService) {
        this.queryEntradaConsumoService = queryEntradaConsumoService;
    }

    @PostMapping("/criarEntradaConsumo")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEntradaConsumo (QueryEntradaConsumo queryEntradaConsumo, Long idItem, Long idEntrada) {
        queryEntradaConsumoService.criarEntradaConsumo(queryEntradaConsumo, idItem, idEntrada);
    }
}
