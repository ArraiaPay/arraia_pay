package org.arraipay.arraiapay.api.controller;

import org.arraipay.arraiapay.api.ApiScaffold;
import org.arraipay.arraiapay.api.dto.Requests.FechamentoCaixaRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fechamentos-caixa")
public class FechamentoCaixaController {
    @PostMapping
    public Object fechar(@RequestBody FechamentoCaixaRequest request) {
        throw ApiScaffold.pendente("POST /fechamentos-caixa");
    }

    @GetMapping("/{id}")
    public Object buscar(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("GET /fechamentos-caixa/{id}");
    }

    @GetMapping
    public Object listar(@RequestParam(name = "inicio", required = false) String inicio,
                         @RequestParam(name = "fim", required = false) String fim,
                         @RequestParam(name = "idOperador", required = false) Long idOperador,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "20") int size,
                         @RequestParam(name = "sort", defaultValue = "dataHora,desc") String sort) {
        throw ApiScaffold.pendente("GET /fechamentos-caixa");
    }
}
