package br.com.zupacademy.frederico.microservice_transacoes.dominio.biometria.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.biometria.Biometria;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    private String fingerprint;

    public void setFingerprint(@NotBlank String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(fingerprint, cartao);
    }
}
