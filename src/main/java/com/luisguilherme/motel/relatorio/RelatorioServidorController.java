package com.luisguilherme.motel.relatorio;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relatorio")
public class RelatorioServidorController {

    private final RelatorioServidorService relatorioServidorService;

    public RelatorioServidorController(RelatorioServidorService relatorioServidorService) {
        this.relatorioServidorService = relatorioServidorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void servidorRelatorio(@RequestParam Integer codServidor, HttpServletResponse response) throws Exception {

        relatorioServidorService.servidorRelatorio(codServidor, response);
    }
}
