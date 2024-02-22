package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.model.EntradaConsumo;
import com.luisguilherme.motel.request.EntradaConsumoRequest;
import com.luisguilherme.motel.service.EntradaConsumoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumo")
public class EntradaConsumoController {

    private final EntradaConsumoService entradaConsumoService;

    public EntradaConsumoController(EntradaConsumoService entradaConsumoService) {
        this.entradaConsumoService = entradaConsumoService;
    }

    @PostMapping("/adicionarConsumo/{idEntrada}/{idItem}")
    @ResponseStatus(HttpStatus.CREATED)
    public EntradaConsumo adicionarConsumo(@PathVariable Long idEntrada, @RequestBody EntradaConsumoRequest entradaConsumoRequest, @PathVariable Long idItem) {
        return entradaConsumoService.adicionarConsumo(idEntrada, entradaConsumoRequest, idItem);
    }

    @DeleteMapping("/deletarConsumoPorId/{idConsumo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletarConsumo(@PathVariable Long idConsumo) {
        return entradaConsumoService.deletarConsumo(idConsumo);
    }

    @GetMapping("/consumosPorEntrada/{idEntrada}")
    @ResponseStatus(HttpStatus.OK)
    public List<EntradaConsumo> obterConsumosPorEntrada(@PathVariable Long idEntrada) {
        return entradaConsumoService.obterConsumosPorEntrada(idEntrada);
    }

}
