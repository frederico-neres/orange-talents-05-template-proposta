package br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto;

import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.ResultadoCarteiraDigital;

public class CarteiraDigitalResponse {

    private String id;
    private ResultadoCarteiraDigital resultado;

    public CarteiraDigitalResponse(String id, ResultadoCarteiraDigital resultado) {
        this.id = id;
        this.resultado = resultado;
    }

    public ResultadoCarteiraDigital getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
