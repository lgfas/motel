package com.luisguilherme.motel.service;

import com.luisguilherme.motel.Enum.StatusDoQuarto;
import com.luisguilherme.motel.Enum.StatusEntrada;
import com.luisguilherme.motel.Enum.StatusPagamento;
import com.luisguilherme.motel.Enum.TipoPagamento;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.QuartosRepository;
import com.luisguilherme.motel.request.EntradaRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

    public Entradas criarEntrada(Long idQuarto, EntradaRequest entradaRequest) {
        Entradas entradas = new Entradas();
        Quartos quartos = quartosRepository.findById(idQuarto).orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado!"));

        if (!quartos.getStatusDoQuarto().equals(StatusDoQuarto.OCUPADO)) {
            quartos.setStatusDoQuarto(StatusDoQuarto.OCUPADO);
            entradas.setPlaca(entradaRequest.placa());
            entradas.setQuartos(quartos);
            entradas.setDataRegistroEntrada(LocalDate.now());
            entradas.setHoraEntrada(LocalTime.now());
            entradas.setStatusEntrada(StatusEntrada.ATIVA);
            entradas.setStatusPagamento(StatusPagamento.PENDENTE);
            entradas.setTotalEntrada(0F);
        } else {
            throw new IllegalArgumentException("Quarto já ocupado!");
        }

        return entradaRepository.save(entradas);
    }

    public Entradas atualizarEntrada(Long idEntrada, Entradas novaEntrada, TipoPagamento tipoPagamento, StatusEntrada statusEntrada) {

        Entradas entradas = obterEntradaPorId(idEntrada);
        entradas.setStatusEntrada(statusEntrada);

        if (statusEntrada.equals(StatusEntrada.FINALIZADA)) {
            finalizarEntrada(idEntrada, tipoPagamento);
        }

        entradas.setPlaca(novaEntrada.getPlaca());


        return entradaRepository.save(entradas);

    }

    public void finalizarEntrada(Long idEntrada, TipoPagamento tipoPagamento) {

        Entradas entradas = obterEntradaPorId(idEntrada);

        entradas.setTipoPagamento(tipoPagamento);
        entradas.setHoraSaida(LocalTime.now());
        calcularTotalPorTempo(entradas);

        Quartos quarto = entradas.getQuartos();
        quarto.setStatusDoQuarto(StatusDoQuarto.NECESSITA_LIMPEZA);
    }

    public void calcularTotalPorTempo(Entradas entradas) {

        var horaEntrada = entradas.getHoraEntrada();
        var dataEntrada = entradas.getDataRegistroEntrada();
        var horaSaida = entradas.getHoraSaida();
        var dataSaida = LocalDate.now();


        LocalDateTime entrada = LocalDateTime.of(dataEntrada, horaEntrada);

        LocalDateTime saida = LocalDateTime.of(dataSaida, horaSaida);

        long minutosPermanencia = Duration.between(entrada, saida).toMinutes();

        double custo = 30.0;

        if (minutosPermanencia > 120) {

            custo += Math.ceil((minutosPermanencia - 120) / 30.0) * 5.0;
        }

        entradas.setTotalEntrada((float) (entradas.getTotalEntrada() + custo));
    }
}
