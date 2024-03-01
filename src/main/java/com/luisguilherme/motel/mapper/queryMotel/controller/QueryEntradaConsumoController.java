package com.luisguilherme.motel.mapper.queryMotel.controller;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntradaConsumo;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryMapaGeral;
import com.luisguilherme.motel.mapper.queryMotel.service.QueryEntradaConsumoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/obterEntradasConsumo")
    public Page<QueryEntradaConsumo> obterEntradasConsumo(Pageable pageable) {
        return queryEntradaConsumoService.obterEntradasConsumo(pageable);
    }

    @DeleteMapping("/deletarEntradaConsumo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEntradaConsumo (@PathVariable Long id) {
        queryEntradaConsumoService.deletarEntradaConsumo(id);
    }

}
