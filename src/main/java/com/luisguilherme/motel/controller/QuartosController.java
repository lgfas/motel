package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.request.QuartosRequest;
import com.luisguilherme.motel.service.QuartosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartosController {

    private final QuartosService quartosService;

    public QuartosController(QuartosService quartosService) {
        this.quartosService = quartosService;
    }

    @GetMapping("/acharTodos")
    public List<Quartos> acharTodosQuartos(){
        return quartosService.acharTodosQuartos();
    }

    @PostMapping("/criarQuarto")
    public Quartos criarQuarto(QuartosRequest quartosRequest) {
        return quartosService.criarQuarto(quartosRequest);
    }
}
