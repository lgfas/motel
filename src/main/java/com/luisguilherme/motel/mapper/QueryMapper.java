package com.luisguilherme.motel.mapper;

import com.luisguilherme.motel.mapper.query.QueryEntradaQuartoResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class QueryMapper {

    public static RowMapper<QueryEntradaQuartoResponse> rowMapperEntradaQuarto = ((rs, rowNum) -> new QueryEntradaQuartoResponse(
            rs.getLong("idEntrada"),
            rs.getString("placa"),
            new QueryEntradaQuartoResponse.QuartoResponse(
                    rs.getLong("numeroQuarto"),
                    rs.getString("descricaoQuarto"),
                    rs.getInt("capacidadePessoa"))

    ));
}
