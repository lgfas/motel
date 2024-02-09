package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.StatusPagamento;
import com.luisguilherme.motel.enums.TipoPagamento;
import com.luisguilherme.motel.model.Entradas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaFixture {

    public static Entradas entradaAtiva() {

        return new Entradas(
                1L,
                LocalDate.now().minusDays(1),
                StatusEntrada.ATIVA,
                TipoPagamento.PENDENTE,
                "ptz2i90",
                null,
                QuartosFixture.quarto(),
                StatusPagamento.PENDENTE,
                0F,
                LocalTime.now()
        );
    }

    public static Entradas entradaAtivaDiferente() {

        return new Entradas(
                3L,
                LocalDate.now(),
                StatusEntrada.ATIVA,
                TipoPagamento.PENDENTE,
                "afs9i14",
                null,
                QuartosFixture.quarto(),
                StatusPagamento.PENDENTE,
                0F,
                LocalTime.now()
        );
    }

    public static Entradas entradaDataAtual() {

        return new Entradas(
                4L,
                LocalDate.now(),
                StatusEntrada.FINALIZADA,
                TipoPagamento.PENDENTE,
                "hjz9r34",
                null,
                QuartosFixture.quarto(),
                StatusPagamento.PENDENTE,
                0F,
                LocalTime.now()
        );
    }

    public static Entradas entradaFinalizada() {


        return new Entradas(
                2L,
                LocalDate.now().minusDays(1),
                StatusEntrada.FINALIZADA,
                TipoPagamento.PENDENTE,
                "hjs3o18",
                null,
                QuartosFixture.quarto(),
                StatusPagamento.PENDENTE,
                0F,
                LocalTime.now()
        );
    }

    public static List<Entradas> entradasList() {

        List<Entradas> entradasList = new ArrayList<>();

        entradasList.add(entradaAtiva());
        entradasList.add(entradaFinalizada());

        return entradasList;
    }

    public static List<Entradas> entradasListAtiva() {

        List<Entradas> entradasList = new ArrayList<>();

        entradasList.add(entradaAtiva());
        entradasList.add(entradaAtivaDiferente());

        return entradasList;
    }

    public static List<Entradas> entradasListDataAtual() {

        List<Entradas> entradasList = new ArrayList<>();

        entradasList.add(entradaAtivaDiferente());
        entradasList.add(entradaDataAtual());

        return entradasList;
    }
}
