package com.luisguilherme.motel.mapper.queryMotel.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntradaConsumo;
import com.luisguilherme.motel.repository.ItensRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QueryEntradaConsumoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    private final QueryEntradaRepository queryEntradaRepository;
    private final QueryItensRepository queryItensRepository;

    public QueryEntradaConsumoRepository(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper, QueryEntradaRepository queryEntradaRepository, QueryItensRepository queryItensRepository,
                                         ItensRepository itensRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.queryEntradaRepository = queryEntradaRepository;
        this.queryItensRepository = queryItensRepository;
    }

    public void criarEntradaConsumo (QueryEntradaConsumo queryEntradaConsumo, Long idItem, Long idEntrada) {

        final var sql = """
                
                INSERT INTO mt04_entrada_consumo
                (mt04_total, fkmt04mt03_codigo_itens, mt04_quantidade, fkmt04mt01_codigo_entradas)
                VALUES
                (?,?,?,?)
                
                """;

        var item = queryItensRepository.buscarPorId(idItem);
        var entrada = queryEntradaRepository.buscarPorId(idEntrada);

        var total = item.valor() * queryEntradaConsumo.quantidade();

        jdbcTemplate.update(sql,
                total,
                item.id(),
                queryEntradaConsumo.quantidade(),
                entrada.id()
                );
    }
}
