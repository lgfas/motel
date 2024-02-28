package com.luisguilherme.motel.mapper.queryMotel.controller;

import com.luisguilherme.motel.mapper.queryMotel.model.QueryItens;
import com.luisguilherme.motel.mapper.queryMotel.service.QueryItensService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queryItens")
public class QueryItensController {

    private final QueryItensService queryItensService;

    public QueryItensController(QueryItensService queryItensService) {
        this.queryItensService = queryItensService;
    }

    @PostMapping("/criarItem")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarItem(QueryItens queryItens) {
        queryItensService.criarItem(queryItens);
    }

    @GetMapping("/obterItens")
    @ResponseStatus(HttpStatus.OK)
    public Page<QueryItens> obterItens (@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return queryItensService.obterItens(pageable, page, size);
    }

    @PutMapping("/atualizarItem")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarItem(QueryItens queryItens) {
        queryItensService.atualizarItem(queryItens);
    }

    @DeleteMapping("/deletarItem/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarItem (@PathVariable Long id) {
        queryItensService.deletarItem(id);
    }

}
