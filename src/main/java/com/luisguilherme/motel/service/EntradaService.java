package com.luisguilherme.motel.service;

import com.luisguilherme.motel.Enum.StatusEntrada;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.QuartosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {
    private final EntradaRepository entradaRepository;
    private final QuartosRepository quartosRepository;

    public EntradaService(EntradaRepository entradaRepository,
                          QuartosRepository quartosRepository) {
        this.entradaRepository = entradaRepository;
        this.quartosRepository = quartosRepository;
    }

    public List<Entradas> obterEntradas() {
        return entradaRepository.findAll();
    }

    public List<Entradas> obterEntradasPorStatusEntrada(StatusEntrada statusEntrada) {
        return entradaRepository.findAllByStatusEntrada(statusEntrada);
    }

    public List<Entradas> obterEntradasPorData(LocalDate dataRegistroEntrada) {
        return entradaRepository.findAllByDataRegistroEntrada(dataRegistroEntrada);
    }

    public List<Entradas> obterEntradasPorDataAtual() {
        LocalDate dataAtual = LocalDate.now();

        return entradaRepository.findAllByDataRegistroEntrada(dataAtual);
    }

    public Entradas obterEntradaPorId(Long id) {
        return entradaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada!"));
    }

    public Entradas criarEntrada(Long idQuarto, Entradas entradas) {

        Quartos quartos = quartosRepository.findById(idQuarto).orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado!"));
        entradas.setQuartos(quartos);
        entradas.setDataRegistroEntrada(LocalDate.now());
        entradas.setHoraEntrada(LocalTime.now());

        return entradaRepository.save(entradas);
    }

    public Entradas atualizarEntrada(Long idEntrada, Entradas novaEntrada) {

        Entradas entradas = obterEntradaPorId(idEntrada);

        entradas.setHoraEntrada(novaEntrada.getHoraEntrada());
        entradas.setHoraSaida(novaEntrada.getHoraSaida());
        entradas.setStatusEntrada(novaEntrada.getStatusEntrada());
        entradas.setTotalEntrada(novaEntrada.getTotalEntrada());
        entradas.setDataRegistroEntrada(novaEntrada.getDataRegistroEntrada());
        entradas.setPlaca(novaEntrada.getPlaca());
        entradas.setStatusPagamento(novaEntrada.getStatusPagamento());
        entradas.setTipoPagamento(novaEntrada.getTipoPagamento());

        return entradaRepository.save(entradas);

    }
}
