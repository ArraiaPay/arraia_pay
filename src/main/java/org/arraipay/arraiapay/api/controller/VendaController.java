package org.arraipay.arraiapay.api.controller;

import org.arraipay.arraiapay.api.ApiScaffold;
import org.arraipay.arraiapay.api.dto.Requests.EstornoRequest;
import org.arraipay.arraiapay.api.dto.Requests.VendaRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {
    @PostMapping
    public Object criar(@RequestHeader("Idempotency-Key") String chaveIdempotencia,
                        @RequestBody VendaRequest request) {
        throw ApiScaffold.pendente("POST /vendas");
    }

    @GetMapping("/{id}")
    public Object buscar(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("GET /vendas/{id}");
    }

    @GetMapping
    public Object listar(@RequestParam(name = "inicio", required = false) String inicio,
                         @RequestParam(name = "fim", required = false) String fim,
                         @RequestParam(name = "idCartao", required = false) Long idCartao,
                         @RequestParam(name = "idBarraca", required = false) Long idBarraca,
                         @RequestParam(name = "status", required = false) String status,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "20") int size,
                         @RequestParam(name = "sort", defaultValue = "dataHora,desc") String sort) {
        throw ApiScaffold.pendente("GET /vendas");
    }

    @PostMapping("/{id}/solicitacoes-estorno")
    public Object solicitarEstorno(@PathVariable("id") Long id, @RequestBody EstornoRequest request) {
        throw ApiScaffold.pendente("POST /vendas/{id}/solicitacoes-estorno");
    }

    @PostMapping("/{id}/estorno")
    public Object estornar(@PathVariable("id") Long id, @RequestBody EstornoRequest request) {
        throw ApiScaffold.pendente("POST /vendas/{id}/estorno");
    }
}
