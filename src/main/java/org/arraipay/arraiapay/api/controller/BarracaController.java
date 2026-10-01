package org.arraipay.arraiapay.api.controller;

import org.arraipay.arraiapay.api.ApiScaffold;
import org.arraipay.arraiapay.api.dto.Requests.BarracaRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/barracas")
public class BarracaController {
    @PostMapping
    public Object criar(@RequestBody BarracaRequest request) {
        throw ApiScaffold.pendente("POST /barracas");
    }

    @GetMapping
    public Object listar(@RequestParam(name = "ativa", required = false) Boolean ativa,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "20") int size,
                         @RequestParam(name = "sort", defaultValue = "nome,asc") String sort) {
        throw ApiScaffold.pendente("GET /barracas");
    }

    @GetMapping("/{id}")
    public Object buscar(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("GET /barracas/{id}");
    }

    @PatchMapping("/{id}")
    public Object atualizar(@PathVariable("id") Long id, @RequestBody BarracaRequest request) {
        throw ApiScaffold.pendente("PATCH /barracas/{id}");
    }
}
