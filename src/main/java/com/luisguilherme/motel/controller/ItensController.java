package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.service.ItensService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItensController {

    private final ItensService itensService;

    public ItensController(ItensService itensService) {
        this.itensService = itensService;
    }

    @GetMapping
    public List<Itens> obterItens() {
        return itensService.obterItens();
    }

    @PostMapping("/criarItem")
    public Itens criarItem(Itens item) {
        return itensService.criarItem(item);
    }


}
