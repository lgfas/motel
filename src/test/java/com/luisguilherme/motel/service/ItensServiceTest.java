package com.luisguilherme.motel.service;

import com.luisguilherme.motel.fixture.ItensFixture;
import com.luisguilherme.motel.fixture.ItensRequestFixture;
import com.luisguilherme.motel.model.Itens;
import com.luisguilherme.motel.repository.ItensRepository;
import com.luisguilherme.motel.request.ItensRequest;
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
class ItensServiceTest {

    @Mock
    ItensRepository itensRepository;

    @InjectMocks
    ItensService itensService;

    Itens item = ItensFixture.item();
    List<Itens> itensList = ItensFixture.itensList();
    ItensRequest itensRequest = ItensRequestFixture.itensRequest();

    @Test
    @DisplayName("Lista todos os itens")
    void obterItens() {

        when(itensRepository.findAll()).thenReturn(itensList);

        itensService.obterItens();

        verify(itensRepository, atLeastOnce()).findAll();
    }

    @Test
    @DisplayName("Cria um item válido")
    void criarItem() {

        when(itensRepository.save(any(Itens.class))).thenReturn(item);

        Itens itemCriado = itensService.criarItem(itensRequest);

        assertThat(itemCriado.getId()).isNotNull();
        assertThat(itemCriado.getDescricao()).isEqualTo(itensRequest.descricao());
        assertThat(itemCriado.getValor()).isEqualTo(itensRequest.valor());

        verify(itensRepository, atLeastOnce()).save(any(Itens.class));
    }
}