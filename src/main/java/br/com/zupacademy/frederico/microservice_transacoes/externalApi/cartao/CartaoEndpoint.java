package br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao;

import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

@FeignClient(name = "cartao", url = "${service.proposta.cartao}")
public interface CartaoEndpoint {

    @RequestMapping(method = RequestMethod.GET, path = "/cartoes?idProposta={idProposta}")
    CartaoResponse getCartaoByIdProposta(@PathVariable("idProposta") UUID idProposta);

    @RequestMapping(method = RequestMethod.POST, path = "/cartoes/{id}/bloqueios")
    CartaoBloqueioResponse notificarBloqueio(@PathVariable("id") String id, @RequestBody @Valid CartaoBloqueioRequest cartaoBloqueioRequest);

    @RequestMapping(method = RequestMethod.POST, path = "/cartoes/{id}/avisos")
    CartaoAvisoViagemResponse notificarAvisoViagem(@PathVariable("id") String id, @RequestBody @Valid CartaoAvisoViagemRequest cartaoAvisoViagemRequest);


}
