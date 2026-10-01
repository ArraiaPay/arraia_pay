package org.arraipay.arraiapay.api.controller;

import org.arraipay.arraiapay.api.ApiScaffold;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioController {
    @GetMapping("/dashboard")
    public Object dashboard(@RequestParam(name = "inicio", required = false) String inicio,
                            @RequestParam(name = "fim", required = false) String fim) {
        throw ApiScaffold.pendente("GET /relatorios/dashboard");
    }

    @GetMapping("/vendas-por-barraca")
    public Object vendasPorBarraca(@RequestParam(name = "inicio", required = false) String inicio,
                                   @RequestParam(name = "fim", required = false) String fim) {
        throw ApiScaffold.pendente("GET /relatorios/vendas-por-barraca");
    }

    @GetMapping("/produtos-mais-vendidos")
    public Object produtosMaisVendidos(@RequestParam(name = "inicio", required = false) String inicio,
                                       @RequestParam(name = "fim", required = false) String fim,
                                       @RequestParam(name = "limite", defaultValue = "10") int limite) {
        throw ApiScaffold.pendente("GET /relatorios/produtos-mais-vendidos");
    }

    @GetMapping("/movimento-por-hora")
    public Object movimentoPorHora(@RequestParam(name = "inicio", required = false) String inicio,
                                   @RequestParam(name = "fim", required = false) String fim) {
        throw ApiScaffold.pendente("GET /relatorios/movimento-por-hora");
    }

    @GetMapping("/conciliacao")
    public Object conciliacao(@RequestParam(name = "inicio", required = false) String inicio,
                              @RequestParam(name = "fim", required = false) String fim) {
        throw ApiScaffold.pendente("GET /relatorios/conciliacao");
    }

    @GetMapping("/extrato-cartao/{id}")
    public Object extratoCartao(@PathVariable("id") Long id,
                                @RequestParam(name = "inicio", required = false) String inicio,
                                @RequestParam(name = "fim", required = false) String fim) {
        throw ApiScaffold.pendente("GET /relatorios/extrato-cartao/{id}");
    }

    @GetMapping("/exportacao")
    public Object exportar(@RequestParam("tipo") String tipo,
                           @RequestParam(name = "formato", defaultValue = "csv") String formato) {
        throw ApiScaffold.pendente("GET /relatorios/exportacao");
    }
}
