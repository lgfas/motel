package com.luisguilherme.motel.service;

import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.repository.ItensRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItensService {

    private final ItensRepository itensRepository;

    public ItensService(ItensRepository itensRepository) {
        this.itensRepository = itensRepository;
    }


    public List<Itens> obterItens() {
        return itensRepository.findAll();
    }

    public Itens criarItem(Itens item) {
        return itensRepository.save(item);
    }
}
