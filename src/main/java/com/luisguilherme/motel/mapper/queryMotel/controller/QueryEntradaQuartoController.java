package com.luisguilherme.motel.mapper.queryMotel.controller;

import com.luisguilherme.motel.mapper.queryMotel.response.QueryEntradaQuartoResponse;
import com.luisguilherme.motel.mapper.queryMotel.service.QueryEntradaQuartoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/query")
public class QueryEntradaQuartoController {

    private final QueryEntradaQuartoService queryEntradaQuartoService;

    public QueryEntradaQuartoController(QueryEntradaQuartoService queryEntradaQuartoService) {
        this.queryEntradaQuartoService = queryEntradaQuartoService;
    }

    @GetMapping("/buscaEntradaQuartoPorId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QueryEntradaQuartoResponse buscaEntradaQuartoPorId(@PathVariable Long id) {
        return queryEntradaQuartoService.buscaEntradaQuartoPorId(id);
    }
}
