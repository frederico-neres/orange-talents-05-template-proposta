package br.com.zupacademy.frederico.microservice_transacoes.api;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.StatusProposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.dto.CadastroPropostaResquest;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.SolicitacaoEndpoint;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.dto.SolicitacaoRequest;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.dto.SolicitacaoResponse;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/propostas")
public class CadastroProposta {

    EntityManager entityManager;
    SolicitacaoEndpoint solicitacaoEndpoint;

    public CadastroProposta(EntityManager entityManager, SolicitacaoEndpoint solicitacaoEndpoint) {
        this.entityManager = entityManager;
        this.solicitacaoEndpoint = solicitacaoEndpoint;
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

        URI uri = uriComponentsBuilder.path("/api/propostas/{id}")
                .buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(proposta);
    }
}
