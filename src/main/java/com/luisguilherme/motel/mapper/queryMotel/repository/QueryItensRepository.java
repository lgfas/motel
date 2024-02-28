package com.luisguilherme.motel.mapper.queryMotel.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryItens;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    public Page<QueryItens> obterItens (int page, int size) {

        final var sql = """
                
                SELECT * FROM mt03_itens;
                
                """;

        final var lista = jdbcTemplate.query(sql, rowMapperQueryItens);

        final var pageRequest = PageRequest.of(page, size);

        return new PageImpl<>(lista, pageRequest, size);
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
}
