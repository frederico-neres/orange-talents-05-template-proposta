package br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.StatusProposta;

public enum ResultadoSolicitacao {
    COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusProposta.ELEGIVEL);

    private StatusProposta statusProposta;

    ResultadoSolicitacao(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public StatusProposta convertToStatusProposta() {
        return this.statusProposta;
    }
}
