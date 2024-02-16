package com.luisguilherme.motel.service;

import com.luisguilherme.motel.fixture.EntradaConsumoFixture;
import com.luisguilherme.motel.fixture.EntradaCosumoRequestFixture;
import com.luisguilherme.motel.fixture.EntradaFixture;
import com.luisguilherme.motel.fixture.ItensFixture;
import com.luisguilherme.motel.model.EntradaConsumo;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.repository.EntradaConsumoRepository;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.ItensRepository;
import com.luisguilherme.motel.request.EntradaConsumoRequest;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EntradaConsumoServiceTest {

    @Mock
    EntradaConsumoRepository entradaConsumoRepository;
    @Mock
    EntradaRepository entradaRepository;
    @Mock
    ItensRepository itensRepository;
    @InjectMocks
    EntradaConsumoService entradaConsumoService;

    Entradas entradaAtivaConsumo = EntradaFixture.entradaAtivaConsumo();
    Entradas entradaFinalizada = EntradaFixture.entradaFinalizada();
    Itens item = ItensFixture.item();
    EntradaConsumoRequest entradaConsumoRequest = EntradaCosumoRequestFixture.entradaConsumoRequest();
    List<EntradaConsumo> entradaConsumoListEntradaAtiva = EntradaConsumoFixture.entradaConsumoListEntradaAtiva();
    EntradaConsumo entradaConsumo = EntradaConsumoFixture.entradaConsumo();

    @Test
    @DisplayName("Cria uma entradaConsumo, com entrada e item válidos, onde a entrada não está finalizada")
    void adicionarConsumo() {
        when(entradaRepository.findById(entradaAtivaConsumo.getId())).thenReturn(Optional.ofNullable(entradaAtivaConsumo));
        when(itensRepository.findById(item.getId())).thenReturn(Optional.ofNullable(item));
        when(entradaConsumoRepository.save(any(EntradaConsumo.class))).thenReturn(entradaConsumo);

        EntradaConsumo entradaConsumoCriada = entradaConsumoService.adicionarConsumo(entradaAtivaConsumo.getId(), entradaConsumoRequest, item.getId());

        //total de consumo -> quantidade * valor do item
        //quantidade == 1 ; valor do item == 2F --> total == 2,00
        var total = entradaConsumoRequest.quantidade() * item.getValor();

        assertNotNull(entradaConsumoCriada);
        assertEquals(entradaConsumoCriada.getTotal(), total);
        assertEquals(entradaAtivaConsumo.getTotalEntrada(),total);

        verify(entradaRepository, atLeastOnce()).findById(entradaConsumo.getId());
        verify(itensRepository, atLeastOnce()).findById(item.getId());
        verify(entradaConsumoRepository, atLeastOnce()).save(any(EntradaConsumo.class));
    }

    @Test
    @DisplayName("Tenta criar uma entradaConsumo, mas entrada não existe")
    void adicionarConsumoEntradaInexistente() {

        when(entradaRepository.findById(entradaAtivaConsumo.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaConsumoService.adicionarConsumo(entradaAtivaConsumo.getId(), entradaConsumoRequest, item.getId()))
                .withMessage("Entrada não encontrada!");
    }

    @Test
    @DisplayName("Tenta criar uma entradaConsumo, mas item não existe")
    void adicionarConsumoItemInexistente() {

        when(entradaRepository.findById(entradaAtivaConsumo.getId())).thenReturn(Optional.ofNullable(entradaAtivaConsumo));
        when(itensRepository.findById(item.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaConsumoService.adicionarConsumo(entradaAtivaConsumo.getId(), entradaConsumoRequest, item.getId()))
                .withMessage("Item inexistente!");
    }

    @Test
    @DisplayName("Tenta criar uma entradaConsumo, mas entrada já está finalizada")
    void adicionarConsumoEntradaFinalizada() {

        when(entradaRepository.findById(entradaFinalizada.getId())).thenReturn(Optional.ofNullable(entradaFinalizada));
        when(itensRepository.findById(item.getId())).thenReturn(Optional.ofNullable(item));

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> entradaConsumoService.adicionarConsumo(entradaFinalizada.getId(), entradaConsumoRequest, item.getId()))
                .withMessage("Entrada já finalizada!");
    }

    @Test
    @DisplayName("Lista todos os consumos por uma Entrada válida")
    void obterConsumosPorEntradaValida() {

        when(entradaRepository.findById(entradaAtivaConsumo.getId())).thenReturn(Optional.ofNullable(entradaAtivaConsumo));
        when(entradaConsumoRepository.findAllByEntradas(entradaAtivaConsumo)).thenReturn(entradaConsumoListEntradaAtiva);

        List<EntradaConsumo> entradaConsumoListObtida = entradaConsumoService.obterConsumosPorEntrada(entradaAtivaConsumo.getId());

        entradaConsumoListObtida.forEach(consumo -> assertEquals(consumo.getEntradas().getId(),entradaAtivaConsumo.getId()));

        verify(entradaRepository, atLeastOnce()).findById(entradaAtivaConsumo.getId());
        verify(entradaConsumoRepository, atLeastOnce()).findAllByEntradas(entradaAtivaConsumo);
    }

    @Test
    @DisplayName("Tenta listar todos os consumos por uma Entrada, mas Entrada não existe")
    void obterConsumosPorEntradaInexistente() {

        when(entradaRepository.findById(entradaAtivaConsumo.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaConsumoService.obterConsumosPorEntrada(entradaAtivaConsumo.getId()))
                .withMessage("Entrada não encontrada!");
    }

    @Test
    @DisplayName("Deleta uma entradaConsumo válida")
    void deletarConsumo() {

        when(entradaConsumoRepository.findById(entradaConsumo.getId())).thenReturn(Optional.ofNullable(entradaConsumo));

        String resultado = entradaConsumoService.deletarConsumo(entradaConsumo.getId());

        assertEquals(resultado, "Consumo apagado com sucesso!");
        verify(entradaConsumoRepository, atLeastOnce()).findById(entradaConsumo.getId());
        verify(entradaConsumoRepository, atLeastOnce()).delete(entradaConsumo);
    }

    @Test
    @DisplayName("Deleta uma entradaConsumo válida")
    void deletarConsumoInexistente() {

        when(entradaConsumoRepository.findById(entradaConsumo.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaConsumoService.deletarConsumo(entradaConsumo.getId()))
                .withMessage("Consumo inexistente!");
    }
}