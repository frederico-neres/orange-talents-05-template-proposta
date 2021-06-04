package br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.dto;

import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.ResultadoSolicitacao;

public class SolicitacaoResponse {

    private String documento;
    private String nome;
    private ResultadoSolicitacao resultadoSolicitacao;
    private String idProposta;

    public SolicitacaoResponse(String documento, String nome,
                               ResultadoSolicitacao resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
