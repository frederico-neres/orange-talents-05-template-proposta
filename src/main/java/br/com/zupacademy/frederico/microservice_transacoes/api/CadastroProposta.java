package br.com.zupacademy.frederico.microservice_transacoes.api;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.dto.CadastroPropostaResquest;
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

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity salvarProposta(@RequestBody @Valid CadastroPropostaResquest cadastroPropostaResquest,
                                         UriComponentsBuilder uriComponentsBuilder) {

        Proposta proposta = cadastroPropostaResquest.toModel();
        entityManager.persist(proposta);

        URI uri = uriComponentsBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
