package br.com.zupacademy.frederico.microservice_transacoes.api;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.StatusProposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.dto.CadastroPropostaResquest;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.SolicitacaoEndpoint;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.dto.SolicitacaoRequest;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.dto.SolicitacaoResponse;

import br.com.zupacademy.frederico.microservice_transacoes.metricas.Metricas;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/propostas")
public class CadastroProposta {

    EntityManager entityManager;
    SolicitacaoEndpoint solicitacaoEndpoint;
    Metricas metricas;

    public CadastroProposta(EntityManager entityManager, SolicitacaoEndpoint solicitacaoEndpoint,
                            Metricas metricas) {
        this.entityManager = entityManager;
        this.solicitacaoEndpoint = solicitacaoEndpoint;
        this.metricas = metricas;
    }

    @PostMapping
    @Transactional()
    public ResponseEntity salvarProposta(@RequestBody @Valid CadastroPropostaResquest cadastroPropostaResquest,
                                         UriComponentsBuilder uriComponentsBuilder) {
        Proposta proposta = cadastroPropostaResquest.toModel();

        StatusProposta statusProposta = null;
        try {
            SolicitacaoResponse solicitacao =
                    solicitacaoEndpoint.solicitacao(new SolicitacaoRequest(proposta));

            statusProposta = solicitacao.getResultadoSolicitacao()
                    .convertToStatusProposta();

        }catch (FeignException.FeignClientException exception) {
            statusProposta = StatusProposta.NAO_ELEGIVEL;
        }

        proposta.mudarStatus(statusProposta);
        entityManager.persist(proposta);

        metricas.counter();
        URI uri = uriComponentsBuilder.path("/api/propostas/acompanhamento/{id}")
                .buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
