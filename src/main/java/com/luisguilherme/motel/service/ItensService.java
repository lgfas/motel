package com.luisguilherme.motel.service;

import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.repository.ItensRepository;
import com.luisguilherme.motel.request.ItensRequest;
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

    public Itens criarItem(ItensRequest itensRequest) {
        Itens item = new Itens();
        item.setDescricao(itensRequest.descricao());
        item.setValor(itensRequest.valor());

        return itensRepository.save(item);
    }
}
