package br.com.zupacademy.frederico.microservice_transacoes.scheduled.cartao;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Bloqueio;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.EstadoCartao;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.StatusProposta;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.CartaoEndpoint;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto.CartaoBloqueioRequest;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto.CartaoResponse;
import br.com.zupacademy.frederico.microservice_transacoes.repository.BloqueioRepository;
import br.com.zupacademy.frederico.microservice_transacoes.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoScheduled {

    PropostaRepository propostaRepository;
    CartaoEndpoint cartaoEndpoint;
    BloqueioRepository bloqueioRepository;

    public CartaoScheduled(PropostaRepository propostaRepository, CartaoEndpoint cartaoEndpoint,
                           BloqueioRepository bloqueioRepository) {
        this.propostaRepository = propostaRepository;
        this.cartaoEndpoint = cartaoEndpoint;
        this.bloqueioRepository = bloqueioRepository;
    }

    @Scheduled(fixedDelay = 50000)
    public void atrelarCartaoAProposta() {

        List<Proposta> propostas  = this.propostaRepository
                .findByStatusAndCartao_Id(StatusProposta.ELEGIVEL, null);

        propostas.forEach(proposta -> {
            try {
                CartaoResponse cartaoResponse = cartaoEndpoint.getCartaoByIdProposta(proposta.getId());
                Cartao cartao = new Cartao(cartaoResponse.getId());

                proposta.atrelarCartao(cartao);
                propostaRepository.save(proposta);
            }catch (FeignException.FeignClientException exception) {}

        });
    }

    @Scheduled(fixedDelay = 150000)
    public void bloquearCartao() {

        List<Bloqueio> BloqueioCartoesSolicitacaoBloqueio = bloqueioRepository
                .findByCartao_EstadoCartao(EstadoCartao.SOLICITACAO_BLOQUEIO);

        BloqueioCartoesSolicitacaoBloqueio.forEach(bloqueio-> {
            Cartao cartao = bloqueio.getCartao();
             try {
                 cartaoEndpoint.notificarBloqueio(cartao.getId(),
                         new CartaoBloqueioRequest("microservice_proposta"));

                 cartao.estadoCartaoParaBloqueado();
                 bloqueioRepository.save(bloqueio);

             } catch (FeignException.FeignClientException exception) {}

        });
    }

}
