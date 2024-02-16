package com.luisguilherme.motel.controller;

import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.request.QuartosRequest;
import com.luisguilherme.motel.service.QuartosService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartosController {

    private final QuartosService quartosService;

    public QuartosController(QuartosService quartosService) {
        this.quartosService = quartosService;
    }

    @GetMapping("/acharTodos")
    @ResponseStatus(HttpStatus.OK)
    public List<Quartos> acharTodosQuartos(){
        return quartosService.acharTodosQuartos();
    }

    @PostMapping("/criarQuarto")
    @ResponseStatus(HttpStatus.CREATED)
    public Quartos criarQuarto(QuartosRequest quartosRequest) {
        return quartosService.criarQuarto(quartosRequest);
    }
}
