package org.arraipay.arraiapay.api.controller;

import org.arraipay.arraiapay.api.ApiScaffold;
import org.arraipay.arraiapay.api.dto.Requests.UsuarioRequest;
import org.arraipay.arraiapay.api.dto.Requests.UsuarioUpdateRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @PostMapping
    public Object criar(@RequestBody UsuarioRequest request) {
        throw ApiScaffold.pendente("POST /usuarios");
    }

    @GetMapping
    public Object listar(@RequestParam(name = "ativo", required = false) Boolean ativo,
                         @RequestParam(name = "perfil", required = false) String perfil,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "20") int size,
                         @RequestParam(name = "sort", defaultValue = "idUsuario,asc") String sort) {
        throw ApiScaffold.pendente("GET /usuarios");
    }

    @GetMapping("/{id}")
    public Object buscar(@PathVariable("id") Long id) {
        throw ApiScaffold.pendente("GET /usuarios/{id}");
    }

    @PatchMapping("/{id}")
    public Object atualizar(@PathVariable("id") Long id, @RequestBody UsuarioUpdateRequest request) {
        throw ApiScaffold.pendente("PATCH /usuarios/{id}");
    }
}
