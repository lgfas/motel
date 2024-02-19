package com.luisguilherme.motel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.fixture.ItensFixture;
import com.luisguilherme.motel.fixture.ItensRequestFixture;
import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.request.ItensRequest;
import com.luisguilherme.motel.service.ItensService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ItensController.class)
class ItensControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItensService itensService;


    List<Itens> itensList = ItensFixture.itensList();

    Itens item = ItensFixture.item();
    ItensRequest itensRequest = ItensRequestFixture.itensRequest();

    static final String URL = "/itens";

    @Test
    void obterItens() throws Exception {

        when(itensService.obterItens()).thenReturn(itensList);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].descricao").value(equalToIgnoringCase("Água")))
                .andExpect(jsonPath("$[0].valor").value(3F))
                .andExpect(jsonPath("$[1].descricao").value(equalToIgnoringCase("Refrigerante")))
                .andExpect(jsonPath("$[1].valor").value(3.50F));


    }

    @Test
    void criarItem() throws Exception {

//        when(itensService.criarItem(itensRequest)).thenReturn(item);
//
//        mockMvc.perform(post(URL + "/criarItem")
//                        .content(mapper.writeValueAsString(request()))
//                .andExpect(status().isCreated());
////                .andExpect(jsonPath("$[0].descricao").value(equalToIgnoringCase("Água")))
////                .andExpect(jsonPath("$[0].valor").value(2F));
    }
}