package br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public class CartaoAvisoViagemRequest {

    @NotBlank
    @JsonProperty
    private String destino;
    @NotNull
    @Future
    @JsonProperty
    private OffsetDateTime validoAte;

    public CartaoAvisoViagemRequest(String destino, OffsetDateTime validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }


}
