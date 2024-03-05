package com.luisguilherme.motel.relatorio;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio")
public class RelatorioServidorController {

    private final RelatorioServidorService relatorioServidorService;

    public RelatorioServidorController(RelatorioServidorService relatorioServidorService) {
        this.relatorioServidorService = relatorioServidorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void servidorRelatorio(Integer codServidor, HttpServletResponse response) throws Exception {
        relatorioServidorService.servidorRelatorio(codServidor, response);
    }
}
