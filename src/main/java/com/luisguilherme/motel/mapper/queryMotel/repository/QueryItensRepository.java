package com.luisguilherme.motel.mapper.queryMotel.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntrada;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryItens;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class QueryItensRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    private final RowMapper<QueryItens> rowMapperQueryItens = ((rs, rowNum) -> new QueryItens(
            rs.getLong("mt03_codigo_itens"),
            rs.getString("mt03_descricao"),
            rs.getFloat("mt03_valor")
    ));


    public QueryItensRepository(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    public void criarItem(QueryItens queryItens) {

        final var sql = """
                
                INSERT INTO
                    mt03_itens (mt03_descricao, mt03_valor) VALUES (?, ?)
                
                """;

        jdbcTemplate.update(sql,
                queryItens.descricao(),
                queryItens.valor()
        );
    }

    public Page<QueryItens> obterItens (Pageable pageable) {

        final var sql = """
                
                SELECT * FROM mt03_itens;
                
                """;

        final var lista = jdbcTemplate.query(sql, rowMapperQueryItens);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        return new PageImpl<>(lista.subList(start, end), pageable, lista.size());
    }

    public void atualizarItem(QueryItens queryItens) {

        final var sql = """
                
                UPDATE mt03_itens SET
                      mt03_descricao = ?,
                      mt03_valor = ?
                WHERE mt03_codigo_itens = ?;
                
                """;

        jdbcTemplate.update(sql,
                queryItens.descricao(),
                queryItens.valor(),
                queryItens.id()
        );
    }

    public void deletarItem (Long id) {

        final var sql = """
                
                DELETE FROM mt03_itens WHERE mt03_codigo_itens = ?
                
                """;

        jdbcTemplate.update(sql, id);
    }

    public QueryItens buscarPorId (Long id) {

        final var sql = """
                
                SELECT * FROM mt03_itens
                WHERE mt03_codigo_itens = ?
                
                """;

        return jdbcTemplate.queryForObject(sql, rowMapperQueryItens, id);
    }
}
