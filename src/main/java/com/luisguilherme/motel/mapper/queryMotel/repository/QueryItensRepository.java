package com.luisguilherme.motel.mapper.queryMotel.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryItens;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class QueryItensRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    private final RowMapper<QueryItens> rowMapperObterItens = ((rs, rowNum) -> new QueryItens(
            rs.getString("descricao"),
            rs.getFloat("valor")
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
}
