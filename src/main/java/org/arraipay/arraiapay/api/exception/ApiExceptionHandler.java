package org.arraipay.arraiapay.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.arraipay.arraiapay.api.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiNotImplementedException.class)
    public ResponseEntity<ApiErrorResponse> endpointPendente(
            ApiNotImplementedException exception, HttpServletRequest request) {
        ApiErrorResponse body = new ApiErrorResponse(
                "NOT_IMPLEMENTED",
                exception.getMessage(),
                null,
                OffsetDateTime.now(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(body);
    }
}
