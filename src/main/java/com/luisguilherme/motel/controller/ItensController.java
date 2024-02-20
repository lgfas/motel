package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.request.ItensRequest;
import com.luisguilherme.motel.service.ItensService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItensController {

    private final ItensService itensService;

    public ItensController(ItensService itensService) {
        this.itensService = itensService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Itens> obterItens() {
        return itensService.obterItens();
    }

    @PostMapping("/criarItem")
    @ResponseStatus(HttpStatus.CREATED)
    public Itens criarItem(@RequestBody ItensRequest itensRequest) {
        return itensService.criarItem(itensRequest);
    }


}
