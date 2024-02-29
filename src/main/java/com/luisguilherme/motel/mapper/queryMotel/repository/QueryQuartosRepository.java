package com.luisguilherme.motel.mapper.queryMotel.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.enums.StatusDoQuarto;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryQuartos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class QueryQuartosRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public QueryQuartosRepository(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    private final RowMapper<QueryQuartos> rowMapperQueryQuartos = ((rs, rowNum) -> new QueryQuartos(
            rs.getLong("mt02_codigo_quartos"),
            rs.getLong("mt02_numero"),
            rs.getString("mt02_descricao"),
            rs.getLong("mt02_capacidade_pessoa"),
            rs.getString("mt02_status_do_quarto")
    ));

    public void criarQuarto(QueryQuartos queryQuartos) {

        final var sql = """
                
                INSERT INTO
                     mt02_quartos (mt02_numero ,mt02_descricao,mt02_capacidade_pessoa,mt02_status_do_quarto) VALUES (?, ?, ?, ?)
                
                """;

        jdbcTemplate.update(sql,
                queryQuartos.id(),
                queryQuartos.descricao(),
                queryQuartos.capacidadePessoa(),
                queryQuartos.statusDoQuarto()
        );
    }

    public Page<QueryQuartos> obterQuartos (Pageable pageable) {

        final var sql = """
                
                SELECT * FROM mt02_quartos;
                
                """;

        final var lista = jdbcTemplate.query(sql, rowMapperQueryQuartos);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());

        return new PageImpl<>(lista.subList(start, end), pageable, lista.size());
    }
}
