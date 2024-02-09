package com.luisguilherme.motel.service;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.StatusPagamento;
import com.luisguilherme.motel.enums.TipoPagamento;
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
import java.time.LocalTime;
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
    List<Entradas> entradasListAtiva = EntradaFixture.entradasListAtiva();
    List<Entradas> entradasListDataAtual = EntradaFixture.entradasListDataAtual();
    Entradas entradaAtiva = EntradaFixture.entradaAtiva();
    Entradas entradaAtivaDiferente = EntradaFixture.entradaAtivaDiferente();
    Entradas entradaFinalizada = EntradaFixture.entradaFinalizada();
    Quartos quartos = QuartosFixture.quartos();
    EntradaRequest entradaRequest = EntradaRequestFixture.entradaRequest();

    @Test
    void converteEntradaResponse() {
    }

    @Test
    @DisplayName("Lista todas as entradas")
    void obterEntradas() {

        when(entradaRepository.findAll()).thenReturn(entradasList);

        entradaService.obterEntradas();

        verify(entradaRepository, atLeastOnce()).findAll();
    }

    @Test
    @DisplayName("Lista todas as entradas com mesmo StatusEntrada")
    void obterEntradasPorStatusEntrada() {

        // Configuração do mock para retornar uma lista de entradas com StatusEntrada ATIVA
        when(entradaRepository.findAllByStatusEntrada(StatusEntrada.ATIVA)).thenReturn(entradasListAtiva);

        // Chama o método obterEntradasPorStatusEntrada
        List<EntradaResponse> entradasPorStatusEntrada = entradaService.obterEntradasPorStatusEntrada(StatusEntrada.ATIVA);

        // Verifica se todas as entradas retornadas têm o StatusEntrada ATIVA
        entradasPorStatusEntrada.forEach(entrada -> assertThat(entrada.statusEntrada()).isEqualTo(StatusEntrada.ATIVA));

        // Verifica se o método correto do repository foi chamado
        verify(entradaRepository, atLeastOnce()).findAllByStatusEntrada(StatusEntrada.ATIVA);
    }

    @Test
    @DisplayName("Lista todas as entradas com mesma dataRegistroEntrada")
    void obterEntradasPorData() {

        var data = LocalDate.now().minusDays(1);

        when(entradaRepository.findAllByDataRegistroEntrada(data)).thenReturn(entradasList);

        List<EntradaResponse> entradasPorDataIgual = entradaService.obterEntradasPorData(LocalDate.now().minusDays(1));

        entradasPorDataIgual.forEach(entrada -> assertThat(entrada.dataRegistroEntrada()).isEqualTo(data));

        verify(entradaRepository, atLeastOnce()).findAllByDataRegistroEntrada(data);

    }

    @Test
    @DisplayName("Lista todas as entradas da dataAtual")
    void obterEntradasPorDataAtual() {

        LocalDate dataAtual = LocalDate.now();

        when(entradaRepository.findAllByDataRegistroEntrada(dataAtual)).thenReturn(entradasListDataAtual);

        List<EntradaResponse> entradasPorDataAtual = entradaService.obterEntradasPorDataAtual();

        entradasPorDataAtual.forEach(entrada -> assertThat(entrada.dataRegistroEntrada()).isEqualTo(dataAtual));

        verify(entradaRepository, atLeastOnce()).findAllByDataRegistroEntrada(dataAtual);

    }

    @Test
    @DisplayName("Devolve entrada de acordo com Id pesquisado")
    void obterEntradaPorId() {

        when(entradaRepository.findById(entradaAtiva.getId())).thenReturn(Optional.ofNullable(entradaAtiva));

        EntradaResponse entradaRetornada = entradaService.obterEntradaPorId(entradaAtiva.getId());

        assertThat(entradaRetornada).isNotNull();

        assertThat(entradaRetornada.id()).isEqualTo(entradaAtiva.getId());

        verify(entradaRepository, atLeastOnce()).findById(entradaAtiva.getId());
    }

    @Test
    @DisplayName("Entrada não encontrada")
    void obterEntradaPorIdEntradaInexistente() {

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaService.obterEntradaPorId(entradaAtiva.getId()))
                .withMessage("Entrada não encontrada!");

    }

    @Test
    @DisplayName("Cria uma entrada válida")
    void criarEntrada() {

        when(quartosRepository.findById(quartos.getId())).thenReturn(Optional.ofNullable(quartos));
        when(entradaRepository.save(any(Entradas.class))).thenReturn(new Entradas());

        Entradas entrada = entradaService.criarEntrada(quartos.getId(), entradaRequest);

        assertThat(entrada).isNotNull();
        assertThat(quartos.getStatusDoQuarto()).isEqualTo(StatusDoQuarto.OCUPADO);

        verify(entradaRepository, atLeastOnce()).save(any(Entradas.class));
    }

    @Test
    @DisplayName("Tenta criar uma entrada, mas o quarto não existe")
    void criarEntradaQuartoInexistente() {

        when(quartosRepository.findById(quartos.getId())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaService.criarEntrada(quartos.getId(), entradaRequest))
                .withMessage("Quarto não encontrado!");
    }

    @Test
    @DisplayName("Tenta criar uma entrada, mas o quarto já está ocupado")
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
    @DisplayName("Atualiza uma entrada válida. (Se statusEntrada = FINALIZADA , tipoPagamento deve ser != de PENDENTE)")
    void atualizarEntrada() {

        StatusEntrada statusEntrada = StatusEntrada.FINALIZADA;
        TipoPagamento tipoPagamento = TipoPagamento.DINHEIRO;

        when(entradaRepository.findById(entradaAtiva.getId())).thenReturn(Optional.ofNullable(entradaAtiva));
        when(entradaRepository.save(any(Entradas.class))).thenReturn(entradaAtiva);

        Entradas entradaAtualizada = entradaService.atualizarEntrada(entradaAtiva.getId(),entradaAtivaDiferente, tipoPagamento, statusEntrada);

        assertThat(entradaAtualizada.getStatusEntrada()).isEqualTo(statusEntrada);
        assertThat(entradaAtualizada.getPlaca()).isEqualTo(entradaAtivaDiferente.getPlaca());

    }

    @Test
    @DisplayName("Tenta atualizar uma entrada, mas a entrada não existe")
    void atualizarEntradaEntradaInexistente() {

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaService.atualizarEntrada(entradaAtiva.getId(),entradaAtivaDiferente, TipoPagamento.PENDENTE, StatusEntrada.ATIVA))
                .withMessage("Entrada não encontrada!");

    }

    @Test
    @DisplayName("Tenta atualizar uma entrada, mas a entrada já está finalizada")
    void atualizarEntradaEntradaFinalizada() {

        when(entradaRepository.findById(entradaFinalizada.getId())).thenReturn(Optional.ofNullable(entradaFinalizada));

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> entradaService.atualizarEntrada(entradaFinalizada.getId(),entradaAtivaDiferente, TipoPagamento.DINHEIRO, StatusEntrada.FINALIZADA))
                .withMessage("Entrada já finalizada!");

    }

    @Test
    @DisplayName("Finaliza uma entrada válida")
    void finalizarEntrada() {

        when(entradaRepository.findById(entradaFinalizada.getId())).thenReturn(Optional.ofNullable(entradaFinalizada));

        entradaService.finalizarEntrada(entradaFinalizada.getId(), TipoPagamento.DINHEIRO);

        //Valor de 24h de estadia, para ser comparado com o valor atribuído no método do service com o totalEntrada
        var custo = 250;

        assertThat(entradaFinalizada.getTipoPagamento()).isEqualTo(TipoPagamento.DINHEIRO);
        assertThat(entradaFinalizada.getHoraSaida()).isNotNull();
        assertThat(entradaFinalizada.getTotalEntrada()).isEqualTo(custo);
        assertThat(entradaFinalizada.getStatusPagamento()).isEqualTo(StatusPagamento.CONCLUIDO);
        assertThat(entradaFinalizada.getQuartos().getStatusDoQuarto()).isEqualTo(StatusDoQuarto.DISPONIVEL);

        verify(entradaRepository, atLeastOnce()).findById(entradaFinalizada.getId());
    }

    @Test
    @DisplayName("Tenta finalizar uma entrada, mas a entrada não existe")
    void finalizarEntradaEntradaInexistente() {

        Long idInexistente = 99L;

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> entradaService.finalizarEntrada(idInexistente, TipoPagamento.DINHEIRO))
                .withMessage("Entrada não encontrada!");
    }

    @Test
    @DisplayName("Tenta finalizar uma entrada, mas tipoPagamento está PENDENTE")
    void finalizarEntradaTipoPagamentoPendente() {

        when(entradaRepository.findById(entradaFinalizada.getId())).thenReturn(Optional.ofNullable(entradaFinalizada));

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> entradaService.finalizarEntrada(entradaFinalizada.getId(), TipoPagamento.PENDENTE))
                .withMessage("Selecione uma opção de pagamento!");
    }

    @Test
    @DisplayName("Calcula tempo de estadia")
    void calcularTotalPorTempo() {

        //A data de entrada foi há 1 dia atrás, exatamente no mesmo horário, totalizando 24h de estadia
        entradaAtiva.setHoraSaida(LocalTime.now());

        entradaService.calcularTotalPorTempo(entradaAtiva);

        //Custo esperado para 24h de estadia
        var custo = 250;

        assertThat(entradaAtiva.getTotalEntrada()).isEqualTo(custo);

    }
}