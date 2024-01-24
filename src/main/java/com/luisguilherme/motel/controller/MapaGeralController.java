package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.model.MapaGeral;
import com.luisguilherme.motel.service.MapaGeralService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mapas")
public class MapaGeralController {

    private final MapaGeralService mapaGeralService;

    public MapaGeralController(MapaGeralService mapaGeralService) {
        this.mapaGeralService = mapaGeralService;
    }

    @GetMapping
    public List<MapaGeral> obterMapas() {
        return mapaGeralService.obterMapas();
    }

    @GetMapping("/obterMapaPorId/{id}")
    public MapaGeral obterMapaPorId(@PathVariable Long id) {
        return mapaGeralService.obterMapaPorId(id);
    }

    @PutMapping("/alterarValor")
    public MapaGeral alterarValor(Long idMapa ,MapaGeral mapaGeral) {
        return mapaGeralService.alterarValor(idMapa, mapaGeral);
    }

}
