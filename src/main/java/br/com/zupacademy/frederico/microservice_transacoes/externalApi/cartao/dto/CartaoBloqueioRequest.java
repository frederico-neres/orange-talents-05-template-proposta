package br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CartaoBloqueioRequest {
    @NotBlank
    @JsonProperty
    private String sistemaResponsavel;

    public CartaoBloqueioRequest(@NotBlank String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
