package com.luisguilherme.motel.mapper.queryMotel;

import com.luisguilherme.motel.mapper.queryMotel.response.QueryEntradaQuartoResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class QueryMotelMapper {

    public static RowMapper<QueryEntradaQuartoResponse> rowMapperEntradaQuarto = ((rs, rowNum) -> new QueryEntradaQuartoResponse(
            rs.getLong("idEntrada"),
            rs.getString("placa"),
            new QueryEntradaQuartoResponse.QuartoResponse(
                    rs.getLong("numeroQuarto"),
                    rs.getString("descricaoQuarto"),
                    rs.getInt("capacidadePessoa"))

    ));
}
