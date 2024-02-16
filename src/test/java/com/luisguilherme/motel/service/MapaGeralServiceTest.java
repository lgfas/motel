package com.luisguilherme.motel.service;

import com.luisguilherme.motel.enums.TipoPagamento;
import com.luisguilherme.motel.fixture.EntradaFixture;
import com.luisguilherme.motel.fixture.MapaGeralFixture;
import com.luisguilherme.motel.model.Entradas;
import com.luisguilherme.motel.model.MapaGeral;
import com.luisguilherme.motel.repository.EntradaRepository;
import com.luisguilherme.motel.repository.MapaGeralRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
class MapaGeralServiceTest {

    @Mock
    MapaGeralRepository mapaGeralRepository;
    @Mock
    EntradaRepository entradaRepository;
    @InjectMocks
    MapaGeralService mapaGeralService;

    List<MapaGeral> mapaGeralList = MapaGeralFixture.mapaGeralList();
    MapaGeral mapaGeral = MapaGeralFixture.mapaGeral();
    Entradas entradaFinalizada = EntradaFixture.entradaFinalizada();

    @Test
    @DisplayName("Lista todos os mapas")
    void obterMapas() {

        when(mapaGeralRepository.findAll()).thenReturn(mapaGeralList);

        mapaGeralService.obterMapas();

        verify(mapaGeralRepository, atLeastOnce()).findAll();
    }

    @Test
    @DisplayName("Obtém mapa com id válido")
    void obterMapaPorId() {

        when(mapaGeralRepository.findById(mapaGeral.getId())).thenReturn(Optional.ofNullable(mapaGeral));

        MapaGeral mapaGeralEncontrado = mapaGeralService.obterMapaPorId(mapaGeral.getId());

        assertThat(mapaGeralEncontrado).isNotNull();
        assertThat(mapaGeralEncontrado.getId()).isEqualTo(mapaGeral.getId());

        verify(mapaGeralRepository, atLeastOnce()).findById(mapaGeral.getId());
    }

    @Test
    @DisplayName("Tenta obter mapa, mas mapa não existe")
    void obterMapaPorIdInexistente() {

        when(entradaRepository.findById(mapaGeral.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> mapaGeralService.obterMapaPorId(mapaGeral.getId()))
                .withMessage("Mapa não encontrado!");
    }

    @Test
    @DisplayName("Cria mapaGeral com entrada válida e pagamento em dinheiro")
    void criarMapaPagamentoDinheiro() {

        when(entradaRepository.findById(entradaFinalizada.getId())).thenReturn(Optional.ofNullable(entradaFinalizada));
        when(mapaGeralRepository.save(any(MapaGeral.class))).thenReturn(mapaGeral);

        entradaFinalizada.setTipoPagamento(TipoPagamento.DINHEIRO);
        entradaFinalizada.setTotalEntrada(100F);

        mapaGeralService.criarMapa(entradaFinalizada.getId());

        // Verificação
        ArgumentCaptor<MapaGeral> mapaCaptor = ArgumentCaptor.forClass(MapaGeral.class);
        verify(mapaGeralRepository, times(1)).save(mapaCaptor.capture());

        MapaGeral mapaGeralCriado = mapaCaptor.getValue();
        assertNotNull(mapaGeralCriado);
        assertEquals(LocalDate.now(), mapaGeralCriado.getData());
        assertEquals("ENTRADA DIA (DINHEIRO)", mapaGeralCriado.getReport());
        assertEquals(100F, mapaGeralCriado.getEntrada());
    }

    @Test
    @DisplayName("Cria mapaGeral com entrada válida e pagamento em pix")
    void criarMapaPagamentoPix() {

        when(entradaRepository.findById(entradaFinalizada.getId())).thenReturn(Optional.ofNullable(entradaFinalizada));
        when(mapaGeralRepository.save(any(MapaGeral.class))).thenReturn(mapaGeral);

        entradaFinalizada.setTipoPagamento(TipoPagamento.PIX);
        entradaFinalizada.setTotalEntrada(100F);

        mapaGeralService.criarMapa(entradaFinalizada.getId());

        // Verificação
        ArgumentCaptor<MapaGeral> mapaCaptor = ArgumentCaptor.forClass(MapaGeral.class);
        verify(mapaGeralRepository, times(1)).save(mapaCaptor.capture());

        MapaGeral mapaGeralCriado = mapaCaptor.getValue();
        assertNotNull(mapaGeralCriado);
        assertEquals(LocalDate.now(), mapaGeralCriado.getData());
        assertEquals("ENTRADA DIA (PIX)", mapaGeralCriado.getReport());
        assertEquals(0F, mapaGeralCriado.getEntrada());
    }

    @Test
    @DisplayName("Cria mapaGeral com entrada válida e pagamento em cartão")
    void criarMapaPagamentoCartao() {

        when(entradaRepository.findById(entradaFinalizada.getId())).thenReturn(Optional.ofNullable(entradaFinalizada));
        when(mapaGeralRepository.save(any(MapaGeral.class))).thenReturn(mapaGeral);

        entradaFinalizada.setTipoPagamento(TipoPagamento.CARTAO);
        entradaFinalizada.setTotalEntrada(100F);

        mapaGeralService.criarMapa(entradaFinalizada.getId());

        // Verificação
        ArgumentCaptor<MapaGeral> mapaCaptor = ArgumentCaptor.forClass(MapaGeral.class);
        verify(mapaGeralRepository, times(1)).save(mapaCaptor.capture());

        MapaGeral mapaGeralCriado = mapaCaptor.getValue();
        assertNotNull(mapaGeralCriado);
        assertEquals(LocalDate.now(), mapaGeralCriado.getData());
        assertEquals("ENTRADA DIA (CARTÃO)", mapaGeralCriado.getReport());
        assertEquals(0F, mapaGeralCriado.getEntrada());
    }

    @Test
    @DisplayName("Tenta criar mapaGeral, mas entrada não existe")
    void criarMapaEntradaInexistente() {

        when(entradaRepository.findById(entradaFinalizada.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> mapaGeralService.criarMapa(entradaFinalizada.getId()))
                .withMessage("Entrada não encontrada!");
    }

    @Test
    @DisplayName("Valor alterado para mapa válido, valor adicionado ao caixa")
    void alterarValorAdicionarAoCaixa() {

        when(mapaGeralRepository.findById(mapaGeral.getId())).thenReturn(Optional.of(mapaGeral));
        when(mapaGeralRepository.calcularTotal()).thenReturn(500F);
        when(mapaGeralRepository.save(any(MapaGeral.class))).thenReturn(mapaGeral);

        MapaGeral novoMapa = new MapaGeral();
        novoMapa.setEntrada(50F);

        MapaGeral resultado = mapaGeralService.alterarValor(mapaGeral.getId(), novoMapa);

        assertNotNull(resultado);
        assertEquals("R$ 50.0 foi adicionado ao caixa.", resultado.getReport());
        // 50 de novoMapa + 30 de mapaGeral
        assertEquals(80F, resultado.getEntrada());
        assertEquals(0F, resultado.getSaida());
        // 50 de novoMapa + 30 de mapaGeral + 500 de calcularTotal
        assertEquals(580F, resultado.getTotal());
        verify(mapaGeralRepository, times(1)).save(mapaGeral);
    }

    @Test
    @DisplayName("Valor alterado para mapa válido, valor retirado do caixa")
    void alterarValorReturadoDoCaixa() {

        when(mapaGeralRepository.findById(mapaGeral.getId())).thenReturn(Optional.of(mapaGeral));
        when(mapaGeralRepository.calcularTotal()).thenReturn(500F);
        when(mapaGeralRepository.save(any(MapaGeral.class))).thenReturn(mapaGeral);

        MapaGeral novoMapa = new MapaGeral();
        novoMapa.setEntrada(-50F);

        MapaGeral resultado = mapaGeralService.alterarValor(mapaGeral.getId(), novoMapa);

        assertNotNull(resultado);
        assertEquals("R$ -50.0 foi retirado do caixa.", resultado.getReport());
        assertEquals(mapaGeral.getEntrada(), resultado.getEntrada());
        // -50 de novoMapa + 30 de mapaGeral
        assertEquals(-50F, resultado.getSaida());
        // -50 de novoMapa + 500 de calcularTotal
        assertEquals(450F, resultado.getTotal());
        verify(mapaGeralRepository, times(1)).save(mapaGeral);
    }

    @Test
    @DisplayName("Tenta alterar valor do mapa, mas mapa não existe")
    void alterarValorMapaInexistente() {

        when(mapaGeralRepository.findById(mapaGeral.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> mapaGeralService.alterarValor(mapaGeral.getId(), mapaGeral))
                .withMessage("Mapa não encontrado!");
    }
}