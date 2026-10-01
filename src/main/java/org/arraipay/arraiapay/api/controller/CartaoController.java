package org.arraipay.arraiapay.api.controller;

import org.arraipay.arraiapay.api.ApiScaffold;
import org.arraipay.arraiapay.api.dto.Requests.CartaoRequest;
import org.arraipay.arraiapay.api.dto.Requests.StatusCartaoRequest;
import org.arraipay.arraiapay.api.dto.Requests.ValidacaoCartaoRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cartoes")
public class CartaoController {
    @PostMapping
    public Object emitir(@RequestBody CartaoRequest request) {
        throw ApiScaffold.pendente("POST /cartoes");
    }

    @GetMapping("/{id}")
    public Object buscar(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("GET /cartoes/{id}");
    }

    @PostMapping("/validacoes")
    public Object validar(@RequestBody ValidacaoCartaoRequest request) {
        throw ApiScaffold.pendente("POST /cartoes/validacoes");
    }

    @GetMapping("/{id}/saldo")
    public Object consultarSaldo(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("GET /cartoes/{id}/saldo");
    }

    @PatchMapping("/{id}/status")
    public Object alterarStatus(@PathVariable("id") Long id, @RequestBody StatusCartaoRequest request) {
        throw ApiScaffold.pendente("PATCH /cartoes/{id}/status");
    }

    @PostMapping("/{id}/segunda-via")
    public Object segundaVia(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("POST /cartoes/{id}/segunda-via");
    }

    @GetMapping("/{id}/extrato")
    public Object extrato(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("GET /cartoes/{id}/extrato");
    }
}
