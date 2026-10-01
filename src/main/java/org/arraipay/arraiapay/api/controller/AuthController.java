package org.arraipay.arraiapay.api.controller;

import org.arraipay.arraiapay.api.ApiScaffold;
import org.arraipay.arraiapay.api.dto.Requests.LoginRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {
        throw ApiScaffold.pendente("POST /auth/login");
    }

    @GetMapping("/me")
    public Object usuarioAtual() {
        throw ApiScaffold.pendente("GET /auth/me");
    }
}
