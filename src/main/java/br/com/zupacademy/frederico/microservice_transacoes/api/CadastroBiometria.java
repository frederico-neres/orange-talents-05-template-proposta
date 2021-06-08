package br.com.zupacademy.frederico.microservice_transacoes.api;


import br.com.zupacademy.frederico.microservice_transacoes.dominio.biometria.Biometria;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.biometria.dto.BiometriaRequest;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/biometrias")
public class CadastroBiometria {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity<?> salvarBiometria(@PathVariable String idCartao,
                                             @RequestBody @Valid BiometriaRequest biometriaRequest,
                                             UriComponentsBuilder componentsBuilder) {
        Cartao cartao = entityManager.find(Cartao.class, idCartao);

        if(cartao == null) {
            return ResponseEntity.notFound().build();
        }

        Biometria biometria = biometriaRequest.toModel(cartao);
        entityManager.persist(biometria);

        URI uri = componentsBuilder.path("/api/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
