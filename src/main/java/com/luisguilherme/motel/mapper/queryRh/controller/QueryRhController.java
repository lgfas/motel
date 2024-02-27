package com.luisguilherme.motel.mapper.queryRh.controller;

import com.luisguilherme.motel.mapper.queryRh.response.QueryCodigoServidorResponse;
import com.luisguilherme.motel.mapper.queryRh.response.QueryMatriculaNomeCpfResponse;
import com.luisguilherme.motel.mapper.queryRh.service.QueryRhService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queryRh")
public class QueryRhController {

    private final QueryRhService queryRhService;

    public QueryRhController(QueryRhService queryRhService) {
        this.queryRhService = queryRhService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/buscaServidorCodigoServidor/{codServidor}")
    public QueryCodigoServidorResponse buscaServidorPorCodigoServidor (@PathVariable Integer codServidor) {
        return queryRhService.buscaServidorPorCodigoServidor(codServidor);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("buscaPorMatriculaNomeCpf/{cpf}/{nome}/{matricula}")
    public Page<QueryMatriculaNomeCpfResponse> buscaPorMatriculaNomeCpf(
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String matricula,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {

        Pageable pageable = PageRequest.of(page, size);
        return queryRhService.buscaPorMatriculaNomeCpf(cpf, nome, matricula, pageable);
    }
}
