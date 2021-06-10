package br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.EstadoCartao;

public class CartaoBloqueioResponse {

    private EstadoCartao resultado;

    public EstadoCartao getResultado() {
        return resultado;
    }

    public void setResultado(EstadoCartao resultado) {
        this.resultado = resultado;
    }
}
