package com.luisguilherme.motel.service;

import com.luisguilherme.motel.Enum.StatusDoQuarto;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.repository.QuartosRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Quartos criarQuarto(Quartos quartos) {

        quartosRepository.save(quartos);

        var quarto = quartosRepository.findById(quartos.getId()).orElseThrow(() -> new EntityNotFoundException("Não achou"));
        quarto.setStatusDoQuarto(StatusDoQuarto.DISPONIVEL);
        quarto.setNumero(quartos.getId());

        return quartosRepository.save(quartos);
    }
}
