package com.luisguilherme.motel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.fixture.ItensFixture;
import com.luisguilherme.motel.fixture.ItensRequestFixture;
import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.request.ItensRequest;
import com.luisguilherme.motel.service.ItensService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ItensController.class)
class ItensControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItensService itensService;

    @Autowired
    private ObjectMapper objectMapper;


    List<Itens> itensList = ItensFixture.itensList();

    Itens item = ItensFixture.item();
    ItensRequest itensRequest = ItensRequestFixture.itensRequest();

    static final String URL = "/itens";

    @Test
    @DisplayName("Lista todos os itens e devolve status 200")
    void obterItens() throws Exception {

        when(itensService.obterItens()).thenReturn(itensList);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].descricao").value("Água"))
                .andExpect(jsonPath("$[0].valor").value(3F))
                .andExpect(jsonPath("$[1].id").hasJsonPath())
                .andExpect(jsonPath("$[1].descricao").value("Refrigerante"))
                .andExpect(jsonPath("$[1].valor").value(3.50F));

        verify(itensService, atLeastOnce()).obterItens();
    }

    @Test
    @DisplayName("Cria um item e devolve status 201")
    void criarItem() throws Exception {

        when(itensService.criarItem(itensRequest)).thenReturn(item);

        mockMvc.perform(post(URL + "/criarItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itensRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(item.getId()))
                .andExpect(jsonPath("$.descricao").value(item.getDescricao()))
                .andExpect(jsonPath("$.valor").value(item.getValor()));

        verify(itensService, atLeastOnce()).criarItem(itensRequest);
    }
}