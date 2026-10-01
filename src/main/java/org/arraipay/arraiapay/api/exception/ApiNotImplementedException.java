package org.arraipay.arraiapay.api.exception;

public class ApiNotImplementedException extends RuntimeException {
    public ApiNotImplementedException(String operacao) {
        super("Endpoint ainda não implementado: " + operacao);
    }
}
