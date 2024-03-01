package com.luisguilherme.motel.mapper.queryMotel.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntradaConsumo;
import com.luisguilherme.motel.repository.ItensRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    private final RowMapper<QueryEntradaConsumo> rowMapperQueryEntradaConsumo = ((rs, rowNum) -> new QueryEntradaConsumo(
            rs.getLong("mt04_codigo_entrada_consumo"),
            rs.getFloat("mt04_total"),
            rs.getLong("fkmt04mt03_codigo_itens"),
            rs.getInt("mt04_quantidade"),
            rs.getLong("fkmt04mt01_codigo_entradas")
    ));

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

    public Page<QueryEntradaConsumo> obterEntradasConsumo (Pageable pageable) {

        final var sql = """
                
                SELECT * FROM mt04_entrada_consumo
                
                """;

        final var lista = jdbcTemplate.query(sql, rowMapperQueryEntradaConsumo);

        int start = (int) pageable.getOffset();

        int end = Math.min((start + pageable.getPageSize()), lista.size());

        return new PageImpl<>(lista.subList(start, end), pageable, lista.size());
    }

    public void deletarEntradaConsumo (Long id) {

        final var sql = """
                
                DELETE FROM mt04_entrada_consumo WHERE mt04_codigo_entrada_consumo = ?
                
                """;

        jdbcTemplate.update(sql, id);
    }
}
