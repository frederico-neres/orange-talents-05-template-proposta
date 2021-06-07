package br.com.zupacademy.frederico.microservice_transacoes.scheduled.cartao;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.StatusProposta;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.CartaoEndpoint;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto.CartaoResponse;
import br.com.zupacademy.frederico.microservice_transacoes.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoScheduled {

    PropostaRepository propostaRepository;
    CartaoEndpoint cartaoEndpoint;

    public CartaoScheduled(PropostaRepository propostaRepository, CartaoEndpoint cartaoEndpoint) {
        this.propostaRepository = propostaRepository;
        this.cartaoEndpoint = cartaoEndpoint;
    }

    @Scheduled(fixedDelay = 50000)
    public void atrelarCartaoAProposta() {

        List<Proposta> propostas  = this.propostaRepository
                .findByStatusAndCartao(StatusProposta.ELEGIVEL, null);

        propostas.forEach(proposta -> {
            try {
                CartaoResponse cartao = cartaoEndpoint.getCartaoByIdProposta(proposta.getId());
                proposta.atrelarCartao(cartao.getId());

                propostaRepository.save(proposta);
            }catch (FeignException.FeignClientException exception) {}

        });
    }
}
