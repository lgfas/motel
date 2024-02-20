package com.luisguilherme.motel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.fixture.QuartosFixture;
import com.luisguilherme.motel.fixture.QuartosRequestFixture;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.request.QuartosRequest;
import com.luisguilherme.motel.service.QuartosService;
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

@WebMvcTest(controllers = QuartosController.class)
class QuartosControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuartosService quartosService;

    @Autowired
    private ObjectMapper objectMapper;

    List<Quartos> quartosList = QuartosFixture.quartosList();

    Quartos quarto = QuartosFixture.quarto();

    QuartosRequest quartosRequest = QuartosRequestFixture.quartosRequest();

    static final String URL = "/quartos";

    @Test
    @DisplayName("Lista todos os quartos e devolve status 200")
    void acharTodosQuartos() throws Exception{

        when(quartosService.acharTodosQuartos()).thenReturn(quartosList);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].numero").value(1L))
                .andExpect(jsonPath("$[0].descricao").value("Quarto com 1 cama de casal e frigobar"))
                .andExpect(jsonPath("$[0].capacidadePessoa").value(2L))
                .andExpect(jsonPath("$[0].statusDoQuarto").value("DISPONIVEL"));

        verify(quartosService, atLeastOnce()).acharTodosQuartos();

    }

    @Test
    @DisplayName("Cria um quarto e devolve status 201")
    void criarQuarto() throws Exception {

        when(quartosService.criarQuarto(quartosRequest)).thenReturn(quarto);

        mockMvc.perform(post(URL + "/criarQuarto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quartosRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(quarto.getId()))
                .andExpect(jsonPath("$.numero").value(quarto.getNumero()))
                .andExpect(jsonPath("$.descricao").value(quarto.getDescricao()))
                .andExpect(jsonPath("$.capacidadePessoa").value(quarto.getCapacidadePessoa()))
                .andExpect(jsonPath("$.statusDoQuarto").value(quarto.getStatusDoQuarto().toString()));

        verify(quartosService, atLeastOnce()).criarQuarto(quartosRequest);
    }
}