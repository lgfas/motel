package com.luisguilherme.motel.service;

import com.luisguilherme.motel.fixture.QuartosFixture;
import com.luisguilherme.motel.fixture.QuartosRequestFixture;
import com.luisguilherme.motel.model.Quartos;
import com.luisguilherme.motel.repository.QuartosRepository;
import com.luisguilherme.motel.request.QuartosRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class QuartosServiceTest {

    @Mock
    QuartosRepository quartosRepository;
    @InjectMocks
    QuartosService quartosService;

    Quartos quarto = QuartosFixture.quarto();
    List<Quartos> quartosList = QuartosFixture.quartosList();
    QuartosRequest quartosRequest = QuartosRequestFixture.quartosRequest();


    @Test
    @DisplayName("Lista todos os quartos")
    void acharTodosQuartos() {

        when(quartosRepository.findAll()).thenReturn(quartosList);

        quartosService.acharTodosQuartos();

        verify(quartosRepository, atLeastOnce()).findAll();
    }

    @Test
    void criarQuarto() {

        when(quartosRepository.save(any(Quartos.class))).thenReturn(quarto);

        Quartos quartoCriado = quartosService.criarQuarto(quartosRequest);

        assertThat(quartoCriado.getId()).isNotNull();
        assertThat(quartoCriado.getNumero()).isEqualTo(quartoCriado.getId());
        assertThat(quartoCriado.getDescricao()).isEqualTo(quartosRequest.descricao());
        assertThat(quartoCriado.getCapacidadePessoa()).isEqualTo(quartosRequest.capacidadePessoa());

        verify(quartosRepository, times(2)).save(any(Quartos.class));
    }
}