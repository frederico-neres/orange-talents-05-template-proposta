package br.com.zupacademy.frederico.microservice_transacoes.exceptionHandler.dto;

import java.util.Collection;

public class ArgumentError {

    private Collection<String> messages;

    public ArgumentError(Collection<String> messages) {
        this.messages = messages;
    }

    public Collection<String> getMesages() {
        return messages;
    }
}
