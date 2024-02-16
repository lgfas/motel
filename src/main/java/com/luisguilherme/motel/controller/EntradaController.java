package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.TipoPagamento;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.request.EntradaRequest;
import com.luisguilherme.motel.response.EntradaResponse;
import com.luisguilherme.motel.service.EntradaService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<EntradaResponse> obterEntradas() {
        return entradaService.obterEntradas();
    }

    @GetMapping("/statusEntrada/{statusEntrada}")
    @ResponseStatus(HttpStatus.OK)
    public List<EntradaResponse> obterEntradasPorStatusEntrada(@PathVariable StatusEntrada statusEntrada) {
        return entradaService.obterEntradasPorStatusEntrada(statusEntrada);
    }

    @GetMapping("/buscaPorData")
    @ResponseStatus(HttpStatus.OK)
    public List<EntradaResponse> obterEntradasPorData(LocalDate data) {
        return entradaService.obterEntradasPorData(data);
    }

    @GetMapping("/buscaPorDataAtual")
    @ResponseStatus(HttpStatus.OK)
    public List<EntradaResponse> obterEntradasPorDataAtual() {
        return entradaService.obterEntradasPorDataAtual();
    }

    @GetMapping("/buscaPorId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntradaResponse obterEntradaPorId(@PathVariable Long id) {
        return entradaService.obterEntradaPorId(id);
    }

    @PostMapping("/criarEntrada")
    @ResponseStatus(HttpStatus.CREATED)
    public Entradas criarEntrada(Long idQuarto, @RequestBody EntradaRequest entradaRequest) {
        return entradaService.criarEntrada(idQuarto, entradaRequest);
    }

    @PutMapping("/atualizarEntrada")
    @ResponseStatus(HttpStatus.OK)
    public Entradas atualizarEntrada(Long idEntrada, Entradas entradas, TipoPagamento tipoPagamento, StatusEntrada statusEntrada) {
        return entradaService.atualizarEntrada(idEntrada,entradas, tipoPagamento, statusEntrada);
    }


}
