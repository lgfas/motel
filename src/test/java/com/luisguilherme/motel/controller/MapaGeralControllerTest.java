package com.luisguilherme.motel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.fixture.MapaGeralFixture;
import com.luisguilherme.motel.model.MapaGeral;
import com.luisguilherme.motel.service.EntradaConsumoService;
import com.luisguilherme.motel.service.MapaGeralService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MapaGeralController.class)
class MapaGeralControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MapaGeralService mapaGeralService;

    @Autowired
    private ObjectMapper objectMapper;

    List<MapaGeral> mapaGeralList = MapaGeralFixture.mapaGeralList();
    MapaGeral mapaGeral = MapaGeralFixture.mapaGeral();
    MapaGeral mapaGeralDiferente = MapaGeralFixture.mapaGeralDiferente();

    static final String URL = "/mapas";

    @Test
    @DisplayName("Lista todos os mapas e devolve status 200")
    void obterMapas() throws Exception {

        when(mapaGeralService.obterMapas()).thenReturn(mapaGeralList);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].apartment").value(mapaGeral.getApartment()))
                .andExpect(jsonPath("$[0].entrada").value(mapaGeral.getEntrada()))
                .andExpect(jsonPath("$[0].report").value(mapaGeral.getReport()))
                .andExpect(jsonPath("$[0].saida").value(mapaGeral.getSaida()))
                .andExpect(jsonPath("$[0].total").value(mapaGeral.getTotal()))
                .andExpect(jsonPath("$[0].hora").hasJsonPath())
                .andExpect(jsonPath("$[0].data").value(mapaGeral.getData().toString()));

        verify(mapaGeralService, atLeastOnce()).obterMapas();
    }

    @Test
    @DisplayName("Obtem mapa por Id e devolve status 200")
    void obterMapaPorId() throws Exception {

        when(mapaGeralService.obterMapaPorId(mapaGeral.getId())).thenReturn(mapaGeral);

        mockMvc.perform(get(URL + "/obterMapaPorId/" + mapaGeral.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.apartment").value(mapaGeral.getApartment()))
                .andExpect(jsonPath("$.entrada").value(mapaGeral.getEntrada()))
                .andExpect(jsonPath("$.report").value(mapaGeral.getReport()))
                .andExpect(jsonPath("$.saida").value(mapaGeral.getSaida()))
                .andExpect(jsonPath("$.total").value(mapaGeral.getTotal()))
                .andExpect(jsonPath("$.hora").hasJsonPath())
                .andExpect(jsonPath("$.data").value(mapaGeral.getData().toString()));

        verify(mapaGeralService, atLeastOnce()).obterMapaPorId(mapaGeral.getId());
    }

    @Test
    @DisplayName("Altera valor do mapa por Id e devolve status 200")
    void alterarValor() throws Exception {

        when(mapaGeralService.alterarValor(anyLong(), any(MapaGeral.class))).thenReturn(mapaGeral);

        mapaGeral.setEntrada(mapaGeralDiferente.getEntrada());
        mapaGeral.setReport(mapaGeralDiferente.getReport());
        mapaGeral.setTotal(mapaGeralDiferente.getTotal());

        mockMvc.perform(put(URL + "/alterarValor/" + mapaGeral.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mapaGeralDiferente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.apartment").value(mapaGeralDiferente.getApartment()))
                .andExpect(jsonPath("$.entrada").value(mapaGeralDiferente.getEntrada()))
                .andExpect(jsonPath("$.report").value(mapaGeralDiferente.getReport()))
                .andExpect(jsonPath("$.saida").value(mapaGeralDiferente.getSaida()))
                .andExpect(jsonPath("$.total").value(mapaGeralDiferente.getTotal()))
                .andExpect(jsonPath("$.hora").hasJsonPath())
                .andExpect(jsonPath("$.data").value(mapaGeralDiferente.getData().toString()));
    }
}