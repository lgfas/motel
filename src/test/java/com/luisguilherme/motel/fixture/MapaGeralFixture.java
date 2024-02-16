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

    public static List<MapaGeral> mapaGeralList() {
        List<MapaGeral> mapaGeralList = new ArrayList<>();

        mapaGeralList.add(mapaGeral());

        return mapaGeralList;
    }
}
