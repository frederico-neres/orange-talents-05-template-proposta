package br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto;

public class CartaoAvisoViagemResponse {

    private ResultadoAvisoViagem resultado;

    public ResultadoAvisoViagem getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoAvisoViagem resultado) {
        this.resultado = resultado;
    }
}
