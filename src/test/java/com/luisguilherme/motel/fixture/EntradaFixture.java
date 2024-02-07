package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.StatusPagamento;
import com.luisguilherme.motel.enums.TipoPagamento;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Quartos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaFixture {

    public static Entradas entradaAtiva() {

        return new Entradas(
                1L,
                LocalDate.now(),
                StatusEntrada.ATIVA,
                TipoPagamento.PENDENTE,
                "ptz2i90",
                null,
                QuartosFixture.quartos(),
                StatusPagamento.PENDENTE,
                0F,
                LocalTime.now()
        );
    }

    public static Entradas entradaFinalizada() {


        return new Entradas(
                2L,
                LocalDate.now(),
                StatusEntrada.FINALIZADA,
                TipoPagamento.PENDENTE,
                "hjs3o18",
                LocalTime.now(),
                QuartosFixture.quartos(),
                StatusPagamento.PENDENTE,
                0F,
                LocalTime.now().minusHours(1)
        );
    }

    public static List<Entradas> entradasList() {

        List<Entradas> entradasList = new ArrayList<>();

        entradasList.add(entradaAtiva());
        entradasList.add(entradaFinalizada());

        return entradasList;
    }
}
