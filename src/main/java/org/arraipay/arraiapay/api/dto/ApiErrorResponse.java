package org.arraipay.arraiapay.api.dto;

import java.time.OffsetDateTime;

public record ApiErrorResponse(
        String codigo,
        String mensagem,
        Object detalhes,
        OffsetDateTime timestamp,
        String caminho) {
}
