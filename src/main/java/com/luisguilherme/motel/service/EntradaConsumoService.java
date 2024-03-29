package com.luisguilherme.motel.service;

import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.model.EntradaConsumo;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.model.builders.EntradaConsumoBuilder;
import com.luisguilherme.motel.repository.EntradaConsumoRepository;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.ItensRepository;
import com.luisguilherme.motel.request.EntradaConsumoRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaConsumoService {

    private final EntradaConsumoRepository entradaConsumoRepository;
    private final EntradaRepository entradaRepository;
    private final ItensRepository itensRepository;

    public EntradaConsumoService(EntradaConsumoRepository entradaConsumoRepository, EntradaRepository entradaRepository,
                                 ItensRepository itensRepository) {
        this.entradaConsumoRepository = entradaConsumoRepository;
        this.entradaRepository = entradaRepository;
        this.itensRepository = itensRepository;
    }


    public EntradaConsumo adicionarConsumo(Long idEntrada, EntradaConsumoRequest entradaConsumoRequest, Long idItem) {
        //EntradaConsumo entradaConsumo = new EntradaConsumo();
        Entradas entradas = entradaRepository.findById(idEntrada).orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada!"));
        Itens item = itensRepository.findById(idItem).orElseThrow(() -> new EntityNotFoundException("Item inexistente!"));

        if (!entradas.getStatusEntrada().equals(StatusEntrada.FINALIZADA)) {
            var total = item.getValor() * entradaConsumoRequest.quantidade();

            EntradaConsumo entradaConsumo = new EntradaConsumoBuilder()
                    .entradas(entradas)
                    .itens(item)
                    .quantidade(entradaConsumoRequest.quantidade())
                    .total(total)
                    .build();

            entradas.setTotalEntrada(entradas.getTotalEntrada() + entradaConsumo.getTotal());

            return entradaConsumoRepository.save(entradaConsumo);
        } else {
            throw new IllegalArgumentException("Entrada já finalizada!");
        }

    }

    public String deletarConsumo(Long idConsumo) {

        EntradaConsumo entradaConsumo = entradaConsumoRepository.findById(idConsumo).orElseThrow(() -> new EntityNotFoundException("Consumo inexistente!"));

        entradaConsumoRepository.delete(entradaConsumo);

        return "Consumo apagado com sucesso!";

    }

    public List<EntradaConsumo> obterConsumosPorEntrada(Long idEntrada) {

        Entradas entradas = entradaRepository.findById(idEntrada).orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada!"));

        return entradaConsumoRepository.findAllByEntradas(entradas);

    }
}
