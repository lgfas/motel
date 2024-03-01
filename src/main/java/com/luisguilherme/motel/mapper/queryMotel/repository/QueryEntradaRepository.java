package com.luisguilherme.motel.mapper.queryMotel.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.enums.StatusPagamento;
import com.luisguilherme.motel.enums.TipoPagamento;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryEntrada;
import com.luisguilherme.motel.mapper.queryMotel.model.QueryMapaGeral;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Repository
public class QueryEntradaRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    private final QueryQuartosRepository queryQuartosRepository;

    public QueryEntradaRepository(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper, QueryQuartosRepository queryQuartosRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.queryQuartosRepository = queryQuartosRepository;
    }

    private final RowMapper<QueryEntrada> rowMapperQueryEntrada = ((rs, rowNum) -> new QueryEntrada(
            rs.getLong("mt01_codigo_entradas"),
            rs.getString("mt01_data_registro_entrada"),
            rs.getString("mt01_status_entrada"),
            rs.getString("mt01_tipo_pagamento"),
            rs.getString("mt01_placa"),
            rs.getString("mt01_hora_saida"),
            rs.getLong("fkmt01mt02_codigo_quartos"),
            rs.getString("mt01_status_pagamento"),
            rs.getFloat("mt01_total_entrada"),
            rs.getString("mt01_hora_entrada")
    ));

    public void criarEntrada (QueryEntrada queryEntrada, Long idQuarto) {

        final var sql = """
                
                INSERT INTO
                mt01_entradas
                (mt01_data_registro_entrada, mt01_status_entrada, mt01_tipo_pagamento, mt01_placa, mt01_hora_saida, fkmt01mt02_codigo_quartos, mt01_status_pagamento, mt01_total_entrada, mt01_hora_entrada)
                VALUES
                (?,?,?,?,?,?,?,?,?)
                
                """;

        var data = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = LocalTime.now().format(formatter);
        Time time = Time.valueOf(horaFormatada);

        var statusEntrada = StatusEntrada.ATIVA.toString();
        var tipoPagamento = TipoPagamento.PENDENTE.toString();
        var statusPagamento = StatusPagamento.PENDENTE.toString();

        var quarto = queryQuartosRepository.buscarPorId(idQuarto);

        if (quarto == null) {
            throw new EntityNotFoundException("Quarto inexistente!");
        }

        jdbcTemplate.update(sql,
                data,
                statusEntrada,
                tipoPagamento,
                queryEntrada.placa(),
                queryEntrada.horaSaida(),
                idQuarto,
                statusPagamento,
                0,
                time
                );
    }

    public Page<QueryEntrada> obterEntradas (Pageable pageable) {

        final var sql = """
                
                SELECT * FROM mt01_entradas
                
                """;

        final var lista = jdbcTemplate.query(sql, rowMapperQueryEntrada);

        int start = (int) pageable.getOffset();

        int end = Math.min((start + pageable.getPageSize()), lista.size());

        return new PageImpl<>(lista.subList(start, end), pageable, lista.size());
    }

    public QueryEntrada buscarPorId (Long id) {

        final var sql = """
                
                SELECT * FROM mt01_entradas
                WHERE mt01_codigo_entradas = ?
                
                """;

        return jdbcTemplate.queryForObject(sql, rowMapperQueryEntrada, id);
    }

    public void atualizarEntrada (Long id, QueryEntrada queryEntrada) {

        final var sql = """
                
                UPDATE mt01_entradas
                SET
                    mt01_status_entrada = ?,
                    mt01_tipo_pagamento = ?,
                    mt01_placa = ?,
                    mt01_hora_saida = ?,
                    fkmt01mt02_codigo_quartos = ?,
                    mt01_status_pagamento = ?
                WHERE mt01_codigo_entradas = ?
                
                """;

        LocalTime horaSaida = LocalTime.parse(queryEntrada.horaSaida());

        jdbcTemplate.update(sql,
                queryEntrada.statusEntrada(),
                queryEntrada.tipoPagamento(),
                queryEntrada.placa(),
                horaSaida,
                queryEntrada.idQuarto(),
                queryEntrada.statusPagamento(),
                id
                );
    }


}
