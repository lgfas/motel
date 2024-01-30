package com.luisguilherme.motel.service;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.model.builders.QuartosBuilder;
import com.luisguilherme.motel.repository.QuartosRepository;
import com.luisguilherme.motel.request.QuartosRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartosService {
    private final QuartosRepository quartosRepository;

    public QuartosService(QuartosRepository quartosRepository) {
        this.quartosRepository = quartosRepository;
    }

    public List<Quartos> acharTodosQuartos(){
        return quartosRepository.findAll();
    }

    public Quartos criarQuarto(QuartosRequest quartosRequest) {

        Quartos quartos = new QuartosBuilder()
                .statusDoQuarto(StatusDoQuarto.DISPONIVEL)
                .descricao(quartosRequest.descricao())
                .capacidadePessoa(quartosRequest.capacidadePessoa())
                .build();

        quartosRepository.save(quartos);
        quartos.setNumero(quartos.getId());

        return quartosRepository.save(quartos);
    }
}
