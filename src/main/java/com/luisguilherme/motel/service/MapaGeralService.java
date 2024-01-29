package com.luisguilherme.motel.service;

import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.MapaGeral;
import com.luisguilherme.motel.model.builders.MapaGeralBuilder;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.MapaGeralRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MapaGeralService {

    private final MapaGeralRepository mapaGeralRepository;
    private final EntradaRepository entradaRepository;

    public MapaGeralService(MapaGeralRepository mapaGeralRepository,
                            EntradaRepository entradaRepository) {
        this.mapaGeralRepository = mapaGeralRepository;
        this.entradaRepository = entradaRepository;
    }


    public List<MapaGeral> obterMapas() {
        return mapaGeralRepository.findAll();
    }

    public void criarMapa(Long idEntrada) {

        Entradas entradas = entradaRepository.findById(idEntrada).orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada!"));

        Float totalMapa = mapaGeralRepository.calcularTotal();
        if (totalMapa == null) {
            totalMapa = 0F;
        }

        String turno = "";

        LocalTime horaAtual = LocalTime.now();

        LocalTime inicioMadrugada = LocalTime.of(0, 0);
        LocalTime fimMadrugada = LocalTime.of(5, 59, 59);

        LocalTime inicioDia = LocalTime.of(6, 0);
        LocalTime fimDia = LocalTime.of(17, 59, 59);

        LocalTime inicioNoite = LocalTime.of(18, 0);
        LocalTime fimNoite = LocalTime.of(23, 59, 59);

        if (horaAtual.isAfter(inicioDia) && horaAtual.isBefore(fimDia)){
            turno = "ENTRADA DIA";
        }
        if (horaAtual.isAfter(inicioNoite) && horaAtual.isBefore(fimNoite) || horaAtual.isAfter(inicioMadrugada) && horaAtual.isBefore(fimMadrugada)){
            turno = "ENTRADA NOITE";
        }

        String report = "";
        Float entrada = 0F;

        switch (entradas.getTipoPagamento()) {
            case PIX:
                report = turno + " (PIX)";
                entrada = 0F;
                totalMapa += 0;
                break;
            case CARTAO:
                report = turno + " (CARTÃO)";
                entrada = 0F;
                totalMapa += 0;
                break;
            case DINHEIRO:
                report = turno + " (DINHEIRO)";
                var valor = entradas.getTotalEntrada();
                entrada = valor;
                totalMapa += valor;
                break;
        }

        MapaGeral mapaGeral = new MapaGeralBuilder()
                .data(LocalDate.now())
                .hora(LocalTime.now())
                .apartment(0)
                .entrada(entrada)
                .saida(0F)
                .report(report)
                .total(totalMapa)
                .build();

        mapaGeralRepository.save(mapaGeral);
    }

    public MapaGeral obterMapaPorId(Long id) {
        return mapaGeralRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Mapa não encontrado!"));
    }

    public MapaGeral alterarValor(Long idMapa, MapaGeral mapaGeral) {
        MapaGeral mapa = mapaGeralRepository.findById(idMapa).orElseThrow(() -> new EntityNotFoundException("Mapa não encontrado!"));

        var report = "";

        if (mapaGeral.getEntrada() >= 0) {
            report = "R$ " + mapaGeral.getEntrada() + " foi adicionado ao caixa.";
            mapa.setReport(report);

            mapa.setEntrada(mapaGeral.getEntrada());
            mapa.setSaida(0F);
            var totalMapa = mapaGeralRepository.calcularTotal();
            mapa.setTotal(totalMapa + mapaGeral.getEntrada());
        } else {
            report = "R$ " + mapaGeral.getEntrada() + " foi retirado do caixa.";
            mapa.setReport(report);

            mapa.setSaida(mapaGeral.getEntrada());
            mapa.setEntrada(0F);
            var totalMapa = mapaGeralRepository.calcularTotal();
            mapa.setTotal(totalMapa + mapaGeral.getEntrada());
        }

        mapa.setHora(LocalTime.now());
        mapa.setData(LocalDate.now());
        mapa.setApartment(0);

        return mapaGeralRepository.save(mapa);
    }
}
