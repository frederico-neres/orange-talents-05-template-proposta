package br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.CarteiraDigital;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.TipoCarteiraDigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociarCartaoRequest {
    @Email
    @NotBlank
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public CarteiraDigital toModel(Cartao cartao, TipoCarteiraDigital carteira) {
        return new CarteiraDigital(cartao, email, carteira);
    }

    public String getEmail() {
        return email;
    }
}
