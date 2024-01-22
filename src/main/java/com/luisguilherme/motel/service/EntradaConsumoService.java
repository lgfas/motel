package com.luisguilherme.motel.service;

import com.luisguilherme.motel.model.EntradaConsumo;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.repository.EntradaConsumoRepository;
import com.luisguilherme.motel.repository.EntradaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaConsumoService {

    private final EntradaConsumoRepository entradaConsumoRepository;

    private final EntradaRepository entradaRepository;

    public EntradaConsumoService(EntradaConsumoRepository entradaConsumoRepository, EntradaRepository entradaRepository) {
        this.entradaConsumoRepository = entradaConsumoRepository;
        this.entradaRepository = entradaRepository;
    }


    public EntradaConsumo adicionarConsumo(Long idEntrada, EntradaConsumo entradaConsumo) {

        Entradas entradas = entradaRepository.findById(idEntrada).orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada!"));

        entradaConsumo.setEntradas(entradas);

        return entradaConsumoRepository.save(entradaConsumo);

    }

    public String deletarConsumo(Long idConsumo) {

        EntradaConsumo entradaConsumo = entradaConsumoRepository.findById(idConsumo).orElseThrow(() -> new EntityNotFoundException("Consumo inexistente!"));

        entradaConsumoRepository.delete(entradaConsumo);

        return "Consumo apagado com sucesso!";

    }

    public List<EntradaConsumo> obterConsumosPorEntrada(Long idEntrada) {

        Entradas entradas = entradaRepository.findById(idEntrada).orElseThrow(() -> new EntityNotFoundException("Entrada não econtrada!"));

        return entradaConsumoRepository.findAllByEntradas(entradas);

    }
}
