package com.luisguilherme.motel.service;

import com.luisguilherme.motel.Enum.StatusDoQuarto;
import com.luisguilherme.motel.model.Quartos;
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
        Quartos quartos = new Quartos();

        quartos.setStatusDoQuarto(StatusDoQuarto.DISPONIVEL);
        quartos.setDescricao(quartosRequest.descricao());
        quartos.setCapacidadePessoa(quartosRequest.capacidadePessoa());
        quartosRepository.save(quartos);
        quartos.setNumero(quartos.getId());

        return quartosRepository.save(quartos);
    }
}
