package org.arraipay.arraiapay.api;

import org.arraipay.arraiapay.api.exception.ApiNotImplementedException;

public final class ApiScaffold {
    private ApiScaffold() { }

    public static ApiNotImplementedException pendente(String operacao) {
        return new ApiNotImplementedException(operacao);
    }
}
