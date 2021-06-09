package br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.StatusProposta;

import java.util.UUID;

public class PropostaAcompanhamentoResponse {

    private UUID id;
    private String documento;
    private String nome;
    private StatusProposta status;
    private String cartao;

    public PropostaAcompanhamentoResponse(Proposta proposta) {
        String documento = proposta.getDocumento();
        String cartao = proposta.getNumeroCartao();

        this.id = proposta.getId();
        this.documento = documento.replaceAll("(?!(?:\\D*\\d){6,8}$|(?:\\D*\\d){1,2}$)\\d", "*");;
        this.nome = proposta.getNome();
        this.status = proposta.getStatus();
        this.cartao = cartao != null ? cartao
                .replaceAll("(?!(?:\\D*\\d){14}$|(?:\\D*\\d){1,3}$)\\d", "*"):null;
    }


    public UUID getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public String getCartao() {
        return cartao;
    }
}
