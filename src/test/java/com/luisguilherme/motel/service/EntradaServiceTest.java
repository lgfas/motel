package com.luisguilherme.motel.service;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.StatusPagamento;
import com.luisguilherme.motel.fixture.EntradaFixture;
import com.luisguilherme.motel.fixture.EntradaRequestFixture;
import com.luisguilherme.motel.fixture.QuartosFixture;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.QuartosRepository;
import com.luisguilherme.motel.request.EntradaRequest;
import com.luisguilherme.motel.response.EntradaResponse;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EntradaServiceTest {

    @Mock
    EntradaRepository entradaRepository;
    @Mock
    QuartosRepository quartosRepository;
    @Mock
    MapaGeralService mapaGeralService;
    @InjectMocks
    EntradaService entradaService;

    List<Entradas> entradasList = EntradaFixture.entradasList();
    Entradas entradaAtiva = EntradaFixture.entradaAtiva();
    List<Entradas> entradasListAtiva = EntradaFixture.entradasListAtiva();

    Quartos quartos = QuartosFixture.quartos();
    EntradaRequest entradaRequest = EntradaRequestFixture.entradaRequest();

    @Test
    void converteEntradaResponse() {
    }

    @Test
    void obterEntradas() {

        when(entradaRepository.findAll()).thenReturn(entradasList);

        entradaService.obterEntradas();

        verify(entradaRepository, atLeastOnce()).findAll();
    }

    @Test
    void obterEntradasPorStatusEntrada() {

        // Configuração do mock para retornar uma lista de entradas com StatusEntrada ATIVA
        when(entradaRepository.findAllByStatusEntrada(StatusEntrada.ATIVA)).thenReturn(entradasListAtiva);

        // Chama o método obterEntradasPorStatusEntrada
        List<EntradaResponse> entradasRetornadas = entradaService.obterEntradasPorStatusEntrada(StatusEntrada.ATIVA);

        // Verifica se todas as entradas retornadas têm o StatusEntrada ATIVA
        for (EntradaResponse entrada : entradasRetornadas) {
            assertThat(entrada.statusEntrada()).isEqualTo(StatusEntrada.ATIVA);
        }

        // Verifica se o método correto do repository foi chamado
        verify(entradaRepository, atLeastOnce()).findAllByStatusEntrada(StatusEntrada.ATIVA);
    }

    @Test
    void obterEntradasPorData() {

        when(entradaRepository.findAllByDataRegistroEntrada(LocalDate.now().minusDays(1))).thenReturn(entradasList);

        entradaService.obterEntradasPorData(LocalDate.now().minusDays(1));

        verify(entradaRepository, atLeastOnce()).findAllByDataRegistroEntrada(LocalDate.now().minusDays(1));

    }

    @Test
    void obterEntradasPorDataAtual() {

        LocalDate dataAtual = LocalDate.now();

        when(entradaRepository.findAllByDataRegistroEntrada(dataAtual)).thenReturn(entradasList);

        entradaService.obterEntradasPorDataAtual();

        verify(entradaRepository, atLeastOnce()).findAllByDataRegistroEntrada(dataAtual);

    }

    @Test
    void obterEntradaPorId() {

        when(entradaRepository.findById(entradaAtiva.getId())).thenReturn(Optional.ofNullable(entradaAtiva));

        entradaService.obterEntradaPorId(entradaAtiva.getId());

        verify(entradaRepository, atLeastOnce()).findById(entradaAtiva.getId());
    }

    @Test
    @DisplayName("Entrada nao encontrada")
    void obterEntradaPorIdEntradaInexistente() {

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaService.obterEntradaPorId(entradaAtiva.getId()))
                .withMessage("Entrada não encontrada!");

    }

    @Test
    void criarEntrada() {

        when(quartosRepository.findById(quartos.getId())).thenReturn(Optional.ofNullable(quartos));
        when(entradaRepository.save(any(Entradas.class))).thenReturn(new Entradas());

        Entradas entrada = entradaService.criarEntrada(quartos.getId(), entradaRequest);

        //PESQUISAR A DIFERENCA DE TER ESSA PARTE DE BAIXO OU NAO
        assertThat(entrada).isNotNull();
        assertThat(quartos.getStatusDoQuarto()).isEqualTo(StatusDoQuarto.OCUPADO);
    }

    @Test
    @DisplayName("Quarto nao encontrado")
    void criarEntradaQuartoInexistente() {

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaService.criarEntrada(quartos.getId(), entradaRequest))
                .withMessage("Quarto não encontrado!");
    }

    @Test
    @DisplayName("Quarto ja ocupado")
    void criarEntradaQuartoOcupado() {

        quartos.setStatusDoQuarto(StatusDoQuarto.OCUPADO);
        when(quartosRepository.findById(quartos.getId())).thenReturn(Optional.of(quartos));

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> entradaService.criarEntrada(quartos.getId(), entradaRequest))
                .withMessage("Quarto já ocupado!");
    }

    @Test
    @DisplayName("Verificação dos detalhes da entrada")
    void criarEntradaVerificarDetalhes() {

        when(quartosRepository.findById(quartos.getId())).thenReturn(Optional.of(quartos));
        when(entradaRepository.save(any(Entradas.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Entradas entrada = entradaService.criarEntrada(quartos.getId(), entradaRequest);

        assertThat(entrada.getPlaca()).isEqualTo(entradaRequest.placa());
        assertThat(entrada.getQuartos()).isEqualTo(quartos);
        assertThat(entrada.getDataRegistroEntrada()).isToday();
        assertThat(entrada.getHoraEntrada()).isNotNull();
        assertThat(entrada.getStatusEntrada()).isEqualTo(StatusEntrada.ATIVA);
        assertThat(entrada.getStatusPagamento()).isEqualTo(StatusPagamento.PENDENTE);
        assertThat(entrada.getTotalEntrada()).isEqualTo(0F);
    }

    @Test
    void atualizarEntrada() {
    }

    @Test
    void finalizarEntrada() {
    }

    @Test
    void calcularTotalPorTempo() {
    }
}