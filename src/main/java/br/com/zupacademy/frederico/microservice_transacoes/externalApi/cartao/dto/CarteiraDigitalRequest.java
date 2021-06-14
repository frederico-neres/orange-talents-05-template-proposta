package br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.TipoCarteiraDigital;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraDigitalRequest {

    @Email
    @NotBlank
    @JsonProperty
    private String email;
    @Enumerated(EnumType.STRING)
    @NotBlank
    @JsonProperty
    private TipoCarteiraDigital carteira;

    public CarteiraDigitalRequest(String email, TipoCarteiraDigital carteira) {
        this.email = email;
        this.carteira = carteira;
    }

}
