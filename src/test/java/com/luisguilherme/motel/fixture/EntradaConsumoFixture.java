package com.luisguilherme.motel.fixture;

import com.luisguilherme.motel.model.EntradaConsumo;

import java.util.ArrayList;
import java.util.List;

public class EntradaConsumoFixture {

    public static EntradaConsumo entradaConsumo () {
        return new EntradaConsumo(
                1L,
                2F,
                ItensFixture.item(),
                1,
                EntradaFixture.entradaAtivaConsumo()
        );
    }

    public static EntradaConsumo entradaConsumoDiferente () {
        return new EntradaConsumo(
                2L,
                3.50F,
                ItensFixture.itemDiferente(),
                1,
                EntradaFixture.entradaAtivaConsumo()
        );
    }

    public static List<EntradaConsumo> entradaConsumoListEntradaAtiva () {

        List<EntradaConsumo> entradaConsumoList = new ArrayList<>();

        entradaConsumoList.add(entradaConsumo());
        entradaConsumoList.add(entradaConsumoDiferente());

        return entradaConsumoList;
    }
}
