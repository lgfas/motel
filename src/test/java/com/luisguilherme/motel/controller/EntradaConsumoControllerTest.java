package com.luisguilherme.motel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.fixture.EntradaConsumoFixture;
import com.luisguilherme.motel.fixture.EntradaConsumoRequestFixture;
import com.luisguilherme.motel.fixture.EntradaFixture;
import com.luisguilherme.motel.fixture.ItensFixture;
import com.luisguilherme.motel.model.EntradaConsumo;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.request.EntradaConsumoRequest;
import com.luisguilherme.motel.service.EntradaConsumoService;
import com.luisguilherme.motel.service.EntradaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EntradaConsumoController.class)
class EntradaConsumoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EntradaConsumoService entradaConsumoService;

    @Autowired
    private ObjectMapper objectMapper;

    EntradaConsumo entradaConsumo = EntradaConsumoFixture.entradaConsumo();
    EntradaConsumo entradaConsumoDiferente = EntradaConsumoFixture.entradaConsumoDiferente();
    List<EntradaConsumo> entradaConsumoListEntradaAtiva = EntradaConsumoFixture.entradaConsumoListEntradaAtiva();
    Entradas entradaAtiva = EntradaFixture.entradaAtiva();
    Itens item = ItensFixture.item();
    Itens itemDiferente = ItensFixture.itemDiferente();
    EntradaConsumoRequest entradaConsumoRequest = EntradaConsumoRequestFixture.entradaConsumoRequest();

    static final String URL = "/consumo";
    @Test
    @DisplayName("Cria uma entradaConsumo e devolve status 200")
    void adicionarConsumo() throws Exception {

        when(entradaConsumoService.adicionarConsumo(entradaAtiva.getId(), entradaConsumoRequest, item.getId())).thenReturn(entradaConsumo);

        mockMvc.perform(post(URL + "/adicionarConsumo/" + entradaAtiva.getId() + "/" + item.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entradaConsumoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.total").value(entradaConsumo.getTotal()))
                .andExpect(jsonPath("$.itens.id").value(item.getId()))
                .andExpect(jsonPath("$.quantidade").value(entradaConsumo.getQuantidade()))
                .andExpect(jsonPath("$.entradas.id").value(entradaAtiva.getId()));

        verify(entradaConsumoService, atLeastOnce()).adicionarConsumo(entradaAtiva.getId(), entradaConsumoRequest, item.getId());
    }

    @Test
    @DisplayName("Deleta uma entradaConsumo por Id e devolve status 204")
    void deletarConsumo() throws Exception {

        when(entradaConsumoService.deletarConsumo(entradaConsumo.getId())).thenReturn("Consumo apagado com sucesso!");

        mockMvc.perform(delete(URL + "/deletarConsumoPorId/" + entradaConsumo.getId()))
                .andExpect(status().isNoContent());

        verify(entradaConsumoService, atLeastOnce()).deletarConsumo(entradaConsumo.getId());
    }

    @Test
    @DisplayName("Lista consumos por Id entrada e devolve status 200")
    void obterConsumosPorEntrada() throws Exception {

        when(entradaConsumoService.obterConsumosPorEntrada(entradaAtiva.getId())).thenReturn(entradaConsumoListEntradaAtiva);

        mockMvc.perform(get(URL + "/consumosPorEntrada/" + entradaAtiva.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].total").value(entradaConsumo.getTotal()))
                .andExpect(jsonPath("$[0].itens.id").value(item.getId()))
                .andExpect(jsonPath("$[0].quantidade").value(entradaConsumo.getQuantidade()))
                .andExpect(jsonPath("$[0].entradas.id").value(entradaAtiva.getId()))
                .andExpect(jsonPath("$[1].id").hasJsonPath())
                .andExpect(jsonPath("$[1].total").value(entradaConsumoDiferente.getTotal()))
                .andExpect(jsonPath("$[1].itens.id").value(itemDiferente.getId()))
                .andExpect(jsonPath("$[1].quantidade").value(entradaConsumoDiferente.getQuantidade()))
                .andExpect(jsonPath("$[1].entradas.id").value(entradaAtiva.getId()));

        verify(entradaConsumoService, atLeastOnce()).obterConsumosPorEntrada(entradaAtiva.getId());
    }
}