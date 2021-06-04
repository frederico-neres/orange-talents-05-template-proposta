package br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao;

import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.dto.SolicitacaoRequest;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.solicitacao.dto.SolicitacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "solicitacao", url = "${service.proposta.solicitacao}")
public interface SolicitacaoEndpoint {

    @RequestMapping(method = RequestMethod.POST, value = "")
    SolicitacaoResponse solicitacao(@RequestBody SolicitacaoRequest solicitacaoRequest);
}
