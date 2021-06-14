package br.com.zupacademy.frederico.microservice_transacoes.api;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.CarteiraDigital;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.TipoCarteiraDigital;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.dto.AssociarCartaoRequest;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.CartaoEndpoint;
import br.com.zupacademy.frederico.microservice_transacoes.externalApi.cartao.dto.CarteiraDigitalRequest;
import br.com.zupacademy.frederico.microservice_transacoes.repository.CarteiraRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartao/{id}/associar")
public class AssociarCartao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    CartaoEndpoint cartaoEndpoint;

    @PostMapping("")
    @Transactional
    public ResponseEntity AssociarPaypal(@PathVariable("id") String id,
                                         @RequestBody @Valid AssociarCartaoRequest associarCartaoRequest,
                                         UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, id);
        if(cartao == null) {
            return ResponseEntity.notFound().build();
        }

        TipoCarteiraDigital carteiraDigital = TipoCarteiraDigital.PAYPAL;

        Optional<CarteiraDigital> byCartaoAndCarteira = carteiraRepository
                .findByCartaoAndCarteira(cartao, carteiraDigital);

        if(byCartaoAndCarteira.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        CarteiraDigital carteira = associarCartaoRequest.toModel(cartao, carteiraDigital);
        try{
           cartaoEndpoint.carteiraDigital(id, new CarteiraDigitalRequest(
                    associarCartaoRequest.getEmail(), carteiraDigital));

            carteiraRepository.save(carteira);
        }
        catch (FeignException.FeignClientException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        URI uri = uriComponentsBuilder.path("/api/cartao/carteira/{id}")
                .buildAndExpand(carteira.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
