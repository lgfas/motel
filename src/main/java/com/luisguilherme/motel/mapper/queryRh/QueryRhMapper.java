package com.luisguilherme.motel.mapper.queryRh;

import com.luisguilherme.motel.mapper.queryRh.response.QueryCodigoServidorResponse;
import com.luisguilherme.motel.mapper.queryRh.response.QueryMatriculaNomeCpfResponse;
import com.luisguilherme.motel.mapper.queryRh.response.QueryServidorRelatorio;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class QueryRhMapper {

    public static RowMapper<QueryCodigoServidorResponse> rowMapperCodigoServidor =
            ((rs, rowNum) -> new QueryCodigoServidorResponse(
                    rs.getInt("codPessoa"),
                    rs.getString("pathFoto"),
                    rs.getInt("codServidor"),
                    rs.getString("nome"),
                    rs.getString("dataDeNascimento"),
                    rs.getInt("idSexo"),
                    rs.getString("descricaoSexo"),
                    rs.getString("nomeMae"),
                    rs.getString("nomePai"),
                    rs.getInt("idPais"),
                    rs.getString("descricaoPais"),
                    rs.getInt("idEstado"),
                    rs.getString("descricaoEstado"),
                    rs.getInt("idMunicipio"),
                    rs.getString("descricaoMunicipio"),
                    rs.getInt("idGrau"),
                    rs.getString("descricaoGrau"),
                    rs.getInt("idEstadoCivil"),
                    rs.getString("descricaoEstadoCivil"),
                    rs.getInt("idGrupoSanguineo"),
                    rs.getString("descricaoGrupoSanguineo"),
                    rs.getString("fatorRh"),
                    rs.getInt("idRacaCor"),
                    rs.getString("descricaoRacaCor"),
                    Boolean.parseBoolean("atestadoFisicoMental"),
                    Boolean.parseBoolean("declaracaoDeBens"),
                    rs.getString("rg"),
                    rs.getString("cpf"),
                    rs.getInt("idOrgaoExpedidor"),
                    rs.getString("descricaoOrgaoExpedidor"),
                    rs.getInt("idUf"),
                    rs.getString("nomeUf"),
                    rs.getString("siglaUf"),
                    rs.getString("dataExpedicaoRg"),
                    rs.getString("tituloEleitor"),
                    rs.getString("zona"),
                    rs.getString("secao"),
                    rs.getString("pisPasep"),
                    rs.getString("certificadoReservista"),
                    rs.getString("serieReservista"),
                    rs.getInt("ufExpedicao"),
                    rs.getString("nomeUfReservista"),
                    rs.getString("siglaUfReservista"),
                    rs.getInt("idTipoCertificado"),
                    rs.getString("descricaoTipoCertificado"),
                    rs.getInt("idCategoriaReservista"),
                    rs.getString("descricaoCategoriaReservista"),
                    rs.getInt("idUfReservista")
            ));

    public static RowMapper<QueryMatriculaNomeCpfResponse> rowMapperMatriculaNomeCpf =
            ((rs, rowNum) -> new QueryMatriculaNomeCpfResponse(
                    rs.getInt("codServidor"),
                    rs.getString("matricula"),
                    rs.getString("nome"),
                    rs.getString("cpf")
            ));

    public static RowMapper<QueryServidorRelatorio> rowMapperServidorRelatorio =
            ((rs, rowNum) -> new QueryServidorRelatorio(
                    rs.getInt("codPessoa"),
                    rs.getString("pathFoto"),
                    rs.getInt("codServidor"),
                    rs.getString("nome"),
                    rs.getString("dataDeNascimento"),
                    rs.getString("descricaoSexo"),
                    rs.getString("nomeMae"),
                    rs.getString("nomePai"),
                    rs.getString("rg"),
                    rs.getString("cpf")
            ));
}
