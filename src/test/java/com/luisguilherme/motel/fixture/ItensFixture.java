package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.model.Itens;

import java.util.ArrayList;
import java.util.List;

public class ItensFixture {

    public static Itens item() {
        return new Itens(
                1L,
                "Água",
                3F
        );
    }

    public static Itens itemDiferente() {
        return new Itens(
                2L,
                "Refrigerante",
                3.50F
        );
    }

    public static List<Itens> itensList () {

        List<Itens> itensList = new ArrayList<>();

        itensList.add(item());
        itensList.add(itemDiferente());

        return itensList;
    }
}
