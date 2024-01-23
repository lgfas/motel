package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.Enum.StatusEntrada;
import com.luisguilherme.motel.Enum.TipoPagamento;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.service.EntradaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/entradas")
public class EntradaController {
    private final EntradaService entradaService;

    public EntradaController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @GetMapping
    public List<Entradas> obterEntradas() {
        return entradaService.obterEntradas();
    }

    @GetMapping("/statusEntrada/{statusEntrada}")
    public List<Entradas> obterEntradasPorStatusEntrada(@PathVariable StatusEntrada statusEntrada) {
        return entradaService.obterEntradasPorStatusEntrada(statusEntrada);
    }

    @GetMapping("/buscaPorData")
    public List<Entradas> obterEntradasPorData(LocalDate data) {
        return entradaService.obterEntradasPorData(data);
    }

    @GetMapping("/buscaPorDataAtual")
    public List<Entradas> obterEntradasPorDataAtual() {
        return entradaService.obterEntradasPorDataAtual();
    }

    @GetMapping("/id/{id}")
    public Entradas obterEntradaPorId(@PathVariable Long id) {
        return entradaService.obterEntradaPorId(id);
    }

    @PostMapping("/criarEntrada")
    public Entradas criarEntrada(Long idQuarto, Entradas entradas) {
        return entradaService.criarEntrada(idQuarto, entradas);
    }

    @PutMapping("/atualizarEntrada")
    public Entradas atualizarEntrada(Long idEntrada, Entradas entradas, TipoPagamento tipoPagamento, StatusEntrada statusEntrada) {
        return entradaService.atualizarEntrada(idEntrada,entradas, tipoPagamento, statusEntrada);
    }



}
