package com.luisguilherme.motel.mapper.queryTeste.service;

import com.luisguilherme.motel.mapper.queryTeste.response.QueryEntradaQuartoResponse;
import com.luisguilherme.motel.mapper.queryTeste.QueryTesteMapper;
import com.luisguilherme.motel.mapper.queryTeste.model.QueryEntradaQuarto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueryEntradaQuartoService {

    private final QueryEntradaQuarto queryEntradaQuarto;

    private final JdbcTemplate jdbcTemplate;

    public QueryEntradaQuartoService(QueryEntradaQuarto queryEntradaQuarto, JdbcTemplate jdbcTemplate) {
        this.queryEntradaQuarto = queryEntradaQuarto;
        this.jdbcTemplate = jdbcTemplate;
    }

    public QueryEntradaQuartoResponse buscaEntradaQuartoPorId(Long id) {

        StringBuilder query = new StringBuilder();

        query.append(queryEntradaQuarto.queryEntradaQuarto(id));

        return jdbcTemplate.queryForObject(query.toString(), QueryTesteMapper.rowMapperEntradaQuarto);
    }
}
