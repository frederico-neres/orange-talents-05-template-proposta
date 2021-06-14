package br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.CarteiraDigital;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.TipoCarteiraDigital;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociarCartaoRequest {
    @Email
    @NotBlank
    private String email;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoCarteiraDigital carteira;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCarteira(TipoCarteiraDigital carteira) {
        this.carteira = carteira;
    }

    public CarteiraDigital toModel(Cartao cartao) {
        return new CarteiraDigital(cartao, email, carteira);
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteiraDigital getCarteira() {
        return carteira;
    }
}
