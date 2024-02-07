package com.luisguilherme.motel.service;

import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.fixture.EntradaFixture;
import com.luisguilherme.motel.fixture.EntradaRequestFixture;
import com.luisguilherme.motel.fixture.QuartosFixture;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.QuartosRepository;
import com.luisguilherme.motel.request.EntradaRequest;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

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


        when(entradaRepository.findAllByStatusEntrada(StatusEntrada.ATIVA)).thenReturn(entradasList);

        entradaService.obterEntradasPorStatusEntrada(StatusEntrada.ATIVA);

        verify(entradaRepository, atLeastOnce()).findAllByStatusEntrada(StatusEntrada.ATIVA);
    }

    @Test
    void obterEntradasPorData() {
    }

    @Test
    void obterEntradasPorDataAtual() {
    }

    @Test
    void obterEntradaPorId() {
    }

    @Test
    @DisplayName("Verifica funcionamento da exception")
    void criarEntradaException() {

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaService.criarEntrada(quartos.getId(), entradaRequest))
                .withMessage("Quarto não encontrado!");
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