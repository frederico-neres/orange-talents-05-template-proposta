package br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.StatusProposta;

import java.util.UUID;

public class PropostaAcompanhamentoResponse {

    private UUID id;
    private String nome;
    private StatusProposta status;
    private String cartao;

    public PropostaAcompanhamentoResponse(Proposta proposta) {

        this.id = proposta.getId();
        this.nome = proposta.getNome();
        this.status = proposta.getStatus();
        this.cartao = proposta.getNumeroCartao();
    }


    public UUID getId() {
        return id;
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
