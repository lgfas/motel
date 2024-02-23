package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.model.MapaGeral;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MapaGeralFixture {

    public static MapaGeral mapaGeral() {
        return new MapaGeral(
                1L,
                0,
                30F,
                "ENTRADA DIA (DINHEIRO)",
                0F,
                0F,
                LocalTime.now(),
                LocalDate.now()
        );
    }

    public static MapaGeral mapaGeralDiferente() {
        return new MapaGeral(
                2L,
                0,
                50F,
                "R$ 50.0 foi adicionado ao caixa.",
                0F,
                80F,
                LocalTime.now(),
                LocalDate.now()
        );
    }

    public static List<MapaGeral> mapaGeralList() {
        List<MapaGeral> mapaGeralList = new ArrayList<>();

        mapaGeralList.add(mapaGeral());

        return mapaGeralList;
    }
}
