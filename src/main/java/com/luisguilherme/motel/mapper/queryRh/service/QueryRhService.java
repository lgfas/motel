package com.luisguilherme.motel.mapper.queryRh.service;

import com.luisguilherme.motel.mapper.queryRh.QueryRhMapper;
import com.luisguilherme.motel.mapper.queryRh.model.QueryRhModel;
import com.luisguilherme.motel.mapper.queryRh.response.QueryCodigoServidorResponse;
import com.luisguilherme.motel.mapper.queryRh.response.QueryMatriculaNomeCpfResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueryRhService {

    private final QueryRhModel queryRhModel;
    private final JdbcTemplate jdbcTemplate;

    public QueryRhService(QueryRhModel queryRhModel, JdbcTemplate jdbcTemplate) {
        this.queryRhModel = queryRhModel;
        this.jdbcTemplate = jdbcTemplate;
    }

    public QueryCodigoServidorResponse buscaServidorPorCodigoServidor(Integer codServidor) {

        StringBuilder query = new StringBuilder();

        query.append(queryRhModel.queryCodigoServidor(codServidor));

        return jdbcTemplate.queryForObject(query.toString(), QueryRhMapper.rowMapperCodigoServidor);
    }

    public Page<QueryMatriculaNomeCpfResponse> buscaPorMatriculaNomeCpf(String cpf, String nome, String matricula, Pageable pageable) {

        StringBuilder query = new StringBuilder();

        query.append(queryRhModel.queryMatriculaNomeCpf(cpf, nome, matricula));

        var page = jdbcTemplate.query(query.toString(), QueryRhMapper.rowMapperMatriculaNomeCpf);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), page.size());

        return new PageImpl<>(page.subList(start, end), pageable, page.size());
    }
}
