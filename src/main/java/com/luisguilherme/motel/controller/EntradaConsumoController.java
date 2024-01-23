package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.model.EntradaConsumo;
import com.luisguilherme.motel.request.EntradaConsumoRequest;
import com.luisguilherme.motel.service.EntradaConsumoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumo")
public class EntradaConsumoController {

    private final EntradaConsumoService entradaConsumoService;

    public EntradaConsumoController(EntradaConsumoService entradaConsumoService) {
        this.entradaConsumoService = entradaConsumoService;
    }

    @PostMapping("/adicionarConsumo")
    public EntradaConsumo adicionarConsumo(Long idEntrada, EntradaConsumoRequest entradaConsumoRequest, Long idItem) {
        return entradaConsumoService.adicionarConsumo(idEntrada, entradaConsumoRequest, idItem);
    }

    @DeleteMapping("/deletarConsumoPorId")
    public String deletarConsumo(Long idConsumo) {
        return entradaConsumoService.deletarConsumo(idConsumo);
    }

    @GetMapping("/consumosPorEntrada")
    public List<EntradaConsumo> obterConsumosPorEntrada(Long idEntrada) {
        return entradaConsumoService.obterConsumosPorEntrada(idEntrada);
    }

}
