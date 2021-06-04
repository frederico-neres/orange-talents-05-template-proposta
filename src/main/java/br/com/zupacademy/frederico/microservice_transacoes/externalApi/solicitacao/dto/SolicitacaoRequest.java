package br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class SolicitacaoRequest {

    @NotNull
    @JsonProperty
    private UUID idProposta;
    @NotBlank
    @JsonProperty
    private String documento;
    @NotBlank
    @JsonProperty
    private String nome;

    public SolicitacaoRequest(Proposta proposta) {
        this.idProposta = proposta.getId();
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
    }

}
