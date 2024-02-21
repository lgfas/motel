package com.luisguilherme.motel.service;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.StatusPagamento;
import com.luisguilherme.motel.enums.TipoPagamento;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.model.builders.EntradaBuilder;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.QuartosRepository;
import com.luisguilherme.motel.request.EntradaRequest;
import com.luisguilherme.motel.response.EntradaResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntradaService {
    private final EntradaRepository entradaRepository;
    private final QuartosRepository quartosRepository;
    private final MapaGeralService mapaGeralService;

    public EntradaService(EntradaRepository entradaRepository,
                          QuartosRepository quartosRepository, MapaGeralService mapaGeralService) {
        this.entradaRepository = entradaRepository;
        this.quartosRepository = quartosRepository;
        this.mapaGeralService = mapaGeralService;
    }

    public EntradaResponse converteEntradaResponse(Entradas entradas) {
        return new EntradaResponse(
                entradas.getId(),
                entradas.getDataRegistroEntrada(),
                entradas.getHoraEntrada(),
                entradas.getStatusEntrada(),
                entradas.getTipoPagamento(),
                entradas.getPlaca(),
                entradas.getHoraSaida(),
                entradas.getQuartos().getNumero(),
                entradas.getStatusPagamento(),
                entradas.getTotalEntrada());
    }

    public List<EntradaResponse> obterEntradas() {
        List<Entradas> entradas = entradaRepository.findAll();
        List<EntradaResponse> entradaResponses = new ArrayList<>();

        entradas.forEach(e -> entradaResponses.add(converteEntradaResponse(e)));

        return entradaResponses;
    }

    public List<EntradaResponse> obterEntradasPorStatusEntrada(StatusEntrada statusEntrada) {
        List<Entradas> entradas = entradaRepository.findAllByStatusEntrada(statusEntrada);
        List<EntradaResponse> entradaResponses = new ArrayList<>();


        entradas.forEach(e -> entradaResponses.add(converteEntradaResponse(e)));
        return entradaResponses;
    }

    public List<EntradaResponse> obterEntradasPorData(LocalDate dataRegistroEntrada) {
        List<Entradas> entradas = entradaRepository.findAllByDataRegistroEntrada(dataRegistroEntrada);
        List<EntradaResponse> entradaResponses = new ArrayList<>();


        entradas.forEach(e -> entradaResponses.add(converteEntradaResponse(e)));

        return entradaResponses;
    }

    public List<EntradaResponse> obterEntradasPorDataAtual() {
        LocalDate dataAtual = LocalDate.now();

        List<Entradas> entradas = entradaRepository.findAllByDataRegistroEntrada(dataAtual);
        List<EntradaResponse> entradaResponses = new ArrayList<>();


        entradas.forEach(e -> entradaResponses.add(converteEntradaResponse(e)));

        return entradaResponses;
    }

    public EntradaResponse obterEntradaPorId(Long id) {
        return converteEntradaResponse(entradaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada!")));
    }

    public Entradas criarEntrada(Long idQuarto, EntradaRequest entradaRequest) {

        Quartos quartos = quartosRepository.findById(idQuarto).orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado!"));

        if (!quartos.getStatusDoQuarto().equals(StatusDoQuarto.OCUPADO)) {
            quartos.setStatusDoQuarto(StatusDoQuarto.OCUPADO);
            Entradas entradas = new EntradaBuilder()
                    .placa(entradaRequest.placa())
                    .quartos(quartos)
                    .dataRegistroEntrada(LocalDate.now())
                    .horaEntrada(LocalTime.now())
                    .statusEntrada(StatusEntrada.ATIVA)
                    .statusPagamento(StatusPagamento.PENDENTE)
                    .totalEntrada(0F)
                    .build();

            return entradaRepository.save(entradas);
        } else {
            throw new IllegalArgumentException("Quarto já ocupado!");
        }

    }

    public Entradas atualizarEntrada(Long idEntrada, Entradas novaEntrada, TipoPagamento tipoPagamento, StatusEntrada statusEntrada) {

        Entradas entradas = entradaRepository.findById(idEntrada).orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada!"));

        if (entradas.getStatusEntrada().equals(StatusEntrada.FINALIZADA)) {
            throw new IllegalArgumentException("Entrada já finalizada!");
        }

        entradas.setStatusEntrada(statusEntrada);

        if (statusEntrada.equals(StatusEntrada.FINALIZADA)) {
            finalizarEntrada(idEntrada, tipoPagamento);
            mapaGeralService.criarMapa(idEntrada);
        }

        entradas.setPlaca(novaEntrada.getPlaca());

        return entradaRepository.save(entradas);

    }

    public void finalizarEntrada(Long idEntrada, TipoPagamento tipoPagamento) {

        Entradas entradas = entradaRepository.findById(idEntrada).orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada!"));

        if (tipoPagamento.equals(TipoPagamento.PENDENTE)) {
            throw new IllegalArgumentException("Selecione uma opção de pagamento!");
        }


        entradas.setTipoPagamento(tipoPagamento);
        entradas.setHoraSaida(LocalTime.now());
        calcularTotalPorTempo(entradas);
        entradas.setStatusPagamento(StatusPagamento.CONCLUIDO);

        Quartos quarto = entradas.getQuartos();
        quarto.setStatusDoQuarto(StatusDoQuarto.DISPONIVEL);
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
