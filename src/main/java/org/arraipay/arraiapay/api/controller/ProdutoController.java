package org.arraipay.arraiapay.api.controller;

import org.arraipay.arraiapay.api.ApiScaffold;
import org.arraipay.arraiapay.api.dto.Requests.MovimentoEstoqueRequest;
import org.arraipay.arraiapay.api.dto.Requests.ProdutoRequest;
import org.arraipay.arraiapay.api.dto.Requests.ProdutoUpdateRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {
    @GetMapping("/barracas/{idBarraca}/cardapio")
    public Object cardapio(@PathVariable("idBarraca") Long idBarraca) {
        throw ApiScaffold.pendente("GET /barracas/{id}/cardapio");
    }

    @PostMapping("/produtos")
    public Object criar(@RequestBody ProdutoRequest request) {
        throw ApiScaffold.pendente("POST /produtos");
    }

    @GetMapping("/produtos/{id}")
    public Object buscar(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("GET /produtos/{id}");
    }

    @PatchMapping("/produtos/{id}")
    public Object atualizar(@PathVariable("id") Long id, @RequestBody ProdutoUpdateRequest request) {
        throw ApiScaffold.pendente("PATCH /produtos/{id}");
    }

    @PostMapping("/produtos/{id}/movimentos-estoque")
    public Object movimentarEstoque(@PathVariable("id") Long id,
                                    @RequestBody MovimentoEstoqueRequest request) {
        throw ApiScaffold.pendente("POST /produtos/{id}/movimentos-estoque");
    }
}
