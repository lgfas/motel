package com.luisguilherme.motel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.TipoPagamento;
import com.luisguilherme.motel.fixture.EntradaFixture;
import com.luisguilherme.motel.fixture.EntradaRequestFixture;
import com.luisguilherme.motel.fixture.EntradaResponseFixture;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.request.EntradaRequest;
import com.luisguilherme.motel.response.EntradaResponse;
import com.luisguilherme.motel.service.EntradaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EntradaController.class)
class EntradaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EntradaService entradaService;

    @Autowired
    private ObjectMapper objectMapper;

    List<Entradas> entradasList = EntradaFixture.entradasList();

    Entradas entradaAtiva = EntradaFixture.entradaAtiva();
    Entradas entradaAtivaDiferente = EntradaFixture.entradaAtivaDiferente();
    Entradas entradaFinalizada = EntradaFixture.entradaFinalizada();
    EntradaRequest entradaRequest = EntradaRequestFixture.entradaRequest();
    EntradaResponse entradaResponseAtiva = EntradaResponseFixture.entradaResponseAtiva();

    List<EntradaResponse> entradaResponseList = EntradaResponseFixture.entradaResponseList();
    List<EntradaResponse> entradaResponseListAtivas = EntradaResponseFixture.entradaResponseListAtivas();
    List<EntradaResponse> entradaResponseListDataAtual = EntradaResponseFixture.entradaResponseListDataAtual();

    static final String URL = "/entradas";

    @Test
    @DisplayName("Lista todos as entradas e devolve status 200")
    void obterEntradas() throws Exception {

        when(entradaService.obterEntradas()).thenReturn(entradaResponseList);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].dataRegistroEntrada").value(entradaAtiva.getDataRegistroEntrada().toString()))
                .andExpect(jsonPath("$[0].horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$[0].statusEntrada").value(entradaAtiva.getStatusEntrada().toString()))
                .andExpect(jsonPath("$[0].tipoPagamento").value(entradaAtiva.getTipoPagamento().toString()))
                .andExpect(jsonPath("$[0].placa").value(entradaAtiva.getPlaca()))
                .andExpect(jsonPath("$[0].horaSaida").value(entradaAtiva.getHoraSaida()))
                .andExpect(jsonPath("$[0].numeroQuarto").value(entradaAtiva.getQuartos().getNumero()))
                .andExpect(jsonPath("$[0].statusPagamento").value(entradaAtiva.getStatusPagamento().toString()))
                .andExpect(jsonPath("$[0].totalEntrada").value(entradaAtiva.getTotalEntrada()))
                .andExpect(jsonPath("$[1].id").hasJsonPath())
                .andExpect(jsonPath("$[1].dataRegistroEntrada").value(entradaFinalizada.getDataRegistroEntrada().toString()))
                .andExpect(jsonPath("$[1].horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$[1].statusEntrada").value(entradaFinalizada.getStatusEntrada().toString()))
                .andExpect(jsonPath("$[1].tipoPagamento").value(entradaFinalizada.getTipoPagamento().toString()))
                .andExpect(jsonPath("$[1].placa").value(entradaFinalizada.getPlaca()))
                .andExpect(jsonPath("$[1].horaSaida").value(entradaFinalizada.getHoraSaida()))
                .andExpect(jsonPath("$[1].numeroQuarto").value(entradaFinalizada.getQuartos().getNumero()))
                .andExpect(jsonPath("$[1].statusPagamento").value(entradaFinalizada.getStatusPagamento().toString()))
                .andExpect(jsonPath("$[1].totalEntrada").value(entradaFinalizada.getTotalEntrada()));

        verify(entradaService, atLeastOnce()).obterEntradas();
    }

    @Test
    @DisplayName("Lista todos as entradas com mesmo StatusEntrada e devolve status 200")
    void obterEntradasPorStatusEntrada() throws Exception {

        StatusEntrada statusEntrada = StatusEntrada.ATIVA;

        when(entradaService.obterEntradasPorStatusEntrada(statusEntrada)).thenReturn(entradaResponseListAtivas);

        mockMvc.perform(get(URL + "/statusEntrada/" + statusEntrada))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].dataRegistroEntrada").value(entradaAtiva.getDataRegistroEntrada().toString()))
                .andExpect(jsonPath("$[0].horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$[0].statusEntrada").value(statusEntrada.toString()))
                .andExpect(jsonPath("$[0].tipoPagamento").value(entradaAtiva.getTipoPagamento().toString()))
                .andExpect(jsonPath("$[0].placa").value(entradaAtiva.getPlaca()))
                .andExpect(jsonPath("$[0].horaSaida").value(entradaAtiva.getHoraSaida()))
                .andExpect(jsonPath("$[0].numeroQuarto").value(entradaAtiva.getQuartos().getNumero()))
                .andExpect(jsonPath("$[0].statusPagamento").value(entradaAtiva.getStatusPagamento().toString()))
                .andExpect(jsonPath("$[0].totalEntrada").value(entradaAtiva.getTotalEntrada()))
                .andExpect(jsonPath("$[1].id").hasJsonPath())
                .andExpect(jsonPath("$[1].dataRegistroEntrada").value(entradaAtivaDiferente.getDataRegistroEntrada().toString()))
                .andExpect(jsonPath("$[1].horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$[1].statusEntrada").value(statusEntrada.toString()))
                .andExpect(jsonPath("$[1].tipoPagamento").value(entradaAtivaDiferente.getTipoPagamento().toString()))
                .andExpect(jsonPath("$[1].placa").value(entradaAtivaDiferente.getPlaca()))
                .andExpect(jsonPath("$[1].horaSaida").value(entradaAtivaDiferente.getHoraSaida()))
                .andExpect(jsonPath("$[1].numeroQuarto").value(entradaAtivaDiferente.getQuartos().getNumero()))
                .andExpect(jsonPath("$[1].statusPagamento").value(entradaAtivaDiferente.getStatusPagamento().toString()))
                .andExpect(jsonPath("$[1].totalEntrada").value(entradaAtivaDiferente.getTotalEntrada()));

        verify(entradaService, atLeastOnce()).obterEntradasPorStatusEntrada(statusEntrada);
    }

    @Test
    @DisplayName("Lista todos as entradas com mesma data e devolve status 200")
    void obterEntradasPorData() throws Exception {

        LocalDate data = LocalDate.now().minusDays(1);

        when(entradaService.obterEntradasPorData(data)).thenReturn(entradaResponseList);

        mockMvc.perform(get(URL + "/buscaPorData/" + data))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].dataRegistroEntrada").value(data.toString()))
                .andExpect(jsonPath("$[0].horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$[0].statusEntrada").value(entradaAtiva.getStatusEntrada().toString()))
                .andExpect(jsonPath("$[0].tipoPagamento").value(entradaAtiva.getTipoPagamento().toString()))
                .andExpect(jsonPath("$[0].placa").value(entradaAtiva.getPlaca()))
                .andExpect(jsonPath("$[0].horaSaida").value(entradaAtiva.getHoraSaida()))
                .andExpect(jsonPath("$[0].numeroQuarto").value(entradaAtiva.getQuartos().getNumero()))
                .andExpect(jsonPath("$[0].statusPagamento").value(entradaAtiva.getStatusPagamento().toString()))
                .andExpect(jsonPath("$[0].totalEntrada").value(entradaAtiva.getTotalEntrada()))
                .andExpect(jsonPath("$[1].id").hasJsonPath())
                .andExpect(jsonPath("$[1].dataRegistroEntrada").value(data.toString()))
                .andExpect(jsonPath("$[1].horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$[1].statusEntrada").value(entradaFinalizada.getStatusEntrada().toString()))
                .andExpect(jsonPath("$[1].tipoPagamento").value(entradaFinalizada.getTipoPagamento().toString()))
                .andExpect(jsonPath("$[1].placa").value(entradaFinalizada.getPlaca()))
                .andExpect(jsonPath("$[1].horaSaida").value(entradaFinalizada.getHoraSaida()))
                .andExpect(jsonPath("$[1].numeroQuarto").value(entradaFinalizada.getQuartos().getNumero()))
                .andExpect(jsonPath("$[1].statusPagamento").value(entradaFinalizada.getStatusPagamento().toString()))
                .andExpect(jsonPath("$[1].totalEntrada").value(entradaFinalizada.getTotalEntrada()));

        verify(entradaService, atLeastOnce()).obterEntradasPorData(data);
    }

    @Test
    @DisplayName("Lista todos as entradas com dataRegistroEntrada = dataAtual e devolve status 200")
    void obterEntradasPorDataAtual() throws Exception {

        LocalDate dataAtual = LocalDate.now();

        when(entradaService.obterEntradasPorDataAtual()).thenReturn(entradaResponseListDataAtual);

        mockMvc.perform(get(URL + "/buscaPorDataAtual"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").hasJsonPath())
                .andExpect(jsonPath("$[0].dataRegistroEntrada").value(dataAtual.toString()))
                .andExpect(jsonPath("$[0].horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$[0].statusEntrada").value(entradaAtivaDiferente.getStatusEntrada().toString()))
                .andExpect(jsonPath("$[0].tipoPagamento").value(entradaAtivaDiferente.getTipoPagamento().toString()))
                .andExpect(jsonPath("$[0].placa").value(entradaAtivaDiferente.getPlaca()))
                .andExpect(jsonPath("$[0].horaSaida").value(entradaAtivaDiferente.getHoraSaida()))
                .andExpect(jsonPath("$[0].numeroQuarto").value(entradaAtivaDiferente.getQuartos().getNumero()))
                .andExpect(jsonPath("$[0].statusPagamento").value(entradaAtivaDiferente.getStatusPagamento().toString()))
                .andExpect(jsonPath("$[0].totalEntrada").value(entradaAtivaDiferente.getTotalEntrada()));

        verify(entradaService, atLeastOnce()).obterEntradasPorDataAtual();
    }

    @Test
    @DisplayName("Obtem entrada por Id e devolve status 200")
    void obterEntradaPorId() throws Exception {

        when(entradaService.obterEntradaPorId(entradaAtiva.getId())).thenReturn(entradaResponseAtiva);

        mockMvc.perform(get(URL + "/buscaPorId/" + entradaAtiva.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.dataRegistroEntrada").value(entradaAtiva.getDataRegistroEntrada().toString()))
                .andExpect(jsonPath("$.horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$.statusEntrada").value(entradaAtiva.getStatusEntrada().toString()))
                .andExpect(jsonPath("$.tipoPagamento").value(entradaAtiva.getTipoPagamento().toString()))
                .andExpect(jsonPath("$.placa").value(entradaAtiva.getPlaca()))
                .andExpect(jsonPath("$.horaSaida").value(entradaAtiva.getHoraSaida()))
                .andExpect(jsonPath("$.numeroQuarto").value(entradaAtiva.getQuartos().getNumero()))
                .andExpect(jsonPath("$.statusPagamento").value(entradaAtiva.getStatusPagamento().toString()))
                .andExpect(jsonPath("$.totalEntrada").value(entradaAtiva.getTotalEntrada()));


        verify(entradaService, atLeastOnce()).obterEntradaPorId(entradaAtiva.getId());
    }

    @Test
    @DisplayName("Cria entrada e devolve status 201")
    void criarEntrada() throws Exception {

        when(entradaService.criarEntrada(entradaAtiva.getQuartos().getId(), entradaRequest)).thenReturn(entradaAtiva);

        mockMvc.perform(post(URL + "/criarEntrada/" + entradaAtiva.getQuartos().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entradaRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.dataRegistroEntrada").value(entradaAtiva.getDataRegistroEntrada().toString()))
                .andExpect(jsonPath("$.horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$.statusEntrada").value(entradaAtiva.getStatusEntrada().toString()))
                .andExpect(jsonPath("$.tipoPagamento").value(entradaAtiva.getTipoPagamento().toString()))
                .andExpect(jsonPath("$.placa").value(entradaAtiva.getPlaca()))
                .andExpect(jsonPath("$.horaSaida").value(entradaAtiva.getHoraSaida()))
                .andExpect(jsonPath("$.quartos.id").value(entradaAtiva.getQuartos().getId()))
                .andExpect(jsonPath("$.statusPagamento").value(entradaAtiva.getStatusPagamento().toString()))
                .andExpect(jsonPath("$.totalEntrada").value(entradaAtiva.getTotalEntrada()));

        verify(entradaService, atLeastOnce()).criarEntrada(entradaAtiva.getQuartos().getId(), entradaRequest);
    }

    @Test
    @DisplayName("Atualiza entrada e devolve status 200")
    void atualizarEntrada() throws Exception{

        TipoPagamento tipoPagamento = TipoPagamento.PENDENTE;

        StatusEntrada statusEntrada = StatusEntrada.ATIVA;

        when(entradaService.atualizarEntrada(anyLong(), any(), any(), any())).thenReturn(entradaAtiva);

        entradaAtiva.setPlaca(entradaAtivaDiferente.getPlaca());

        mockMvc.perform(put(URL + "/atualizarEntrada/" + entradaAtiva.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entradaAtivaDiferente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").hasJsonPath())
                //.andExpect(jsonPath("$.dataRegistroEntrada").value(entradaAtivaDiferente.getDataRegistroEntrada().toString()))
                .andExpect(jsonPath("$.horaEntrada").hasJsonPath())
                .andExpect(jsonPath("$.statusEntrada").value(entradaAtivaDiferente.getStatusEntrada().toString()))
                .andExpect(jsonPath("$.tipoPagamento").value(entradaAtivaDiferente.getTipoPagamento().toString()))
                .andExpect(jsonPath("$.placa").value(entradaAtivaDiferente.getPlaca()))
                //.andExpect(jsonPath("$.horaSaida").value(entradaAtivaDiferente.getHoraSaida()))
                .andExpect(jsonPath("$.quartos.id").value(entradaAtivaDiferente.getQuartos().getId()))
                .andExpect(jsonPath("$.statusPagamento").value(entradaAtivaDiferente.getStatusPagamento().toString()))
                .andExpect(jsonPath("$.totalEntrada").value(entradaAtivaDiferente.getTotalEntrada()));

        verify(entradaService, atLeastOnce()).atualizarEntrada(anyLong(), any(), any(), any());
    }
}