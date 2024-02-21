package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.StatusPagamento;
import com.luisguilherme.motel.enums.TipoPagamento;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.response.EntradaResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaResponseFixture {

    public static EntradaResponse entradaResponseAtiva() {

        return new EntradaResponse(
                1L,
                LocalDate.now().minusDays(1),
                LocalTime.of(10,10),
                StatusEntrada.ATIVA,
                TipoPagamento.PENDENTE,
                "ptz2i90",
        null,
                QuartosFixture.quarto().getNumero(),
                StatusPagamento.PENDENTE,
        0F
        );
    }

    public static EntradaResponse entradaResponseFinalizada() {

        return new EntradaResponse(
                3L,
                LocalDate.now().minusDays(1),
                LocalTime.now(),
                StatusEntrada.FINALIZADA,
                TipoPagamento.PENDENTE,
                "hjs3o18",
                null,
                QuartosFixture.quarto().getNumero(),
                StatusPagamento.PENDENTE,
                0F
        );
    }

    public static EntradaResponse entradaResponseAtivaDiferente() {

        return new EntradaResponse(
                3L,
                LocalDate.now(),
                LocalTime.now(),
                StatusEntrada.ATIVA,
                TipoPagamento.PENDENTE,
                "afs9i14",
                null,
                QuartosFixture.quarto().getNumero(),
                StatusPagamento.PENDENTE,
                0F
        );
    }

    public static List<EntradaResponse> entradaResponseList() {

        List<EntradaResponse> entradaResponseList = new ArrayList<>();

        entradaResponseList.add(entradaResponseAtiva());
        entradaResponseList.add(entradaResponseFinalizada());

        return entradaResponseList;
    }

    public static List<EntradaResponse> entradaResponseListAtivas() {

        List<EntradaResponse> entradaResponseList = new ArrayList<>();

        entradaResponseList.add(entradaResponseAtiva());
        entradaResponseList.add(entradaResponseAtivaDiferente());

        return entradaResponseList;
    }

    public static List<EntradaResponse> entradaResponseListDataAtual() {

        List<EntradaResponse> entradaResponseList = new ArrayList<>();

        entradaResponseList.add(entradaResponseAtivaDiferente());

        return entradaResponseList;
    }
}
