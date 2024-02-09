package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.model.Quartos;

import java.util.ArrayList;
import java.util.List;

public class QuartosFixture {

    public static Quartos quarto() {

        return new Quartos(
                1L,
                1L,
                "Quarto com 1 cama de casal e frigobar",
                2L,
                StatusDoQuarto.DISPONIVEL
        );
    }

    public static List<Quartos> quartosList () {

        List<Quartos> quartosList = new ArrayList<>();

        quartosList.add(quarto());

        return quartosList;
    }

}
