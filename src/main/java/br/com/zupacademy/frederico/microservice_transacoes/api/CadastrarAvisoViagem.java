package br.com.zupacademy.frederico.microservice_transacoes.api;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.AvisoViagem;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.dto.AvisoViagemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cartao")
public class CadastrarAvisoViagem {


    @PersistenceContext
    EntityManager entityManager;

    @PostMapping("/{id}/aviso/viagem")
    @Transactional
    public ResponseEntity<?> salvarAvisoViagem(@PathVariable("id") String id,
                                               @RequestBody @Valid AvisoViagemRequest avisoViagemRequest,
                                               HttpServletRequest request) {

        Cartao cartao = entityManager.find(Cartao.class, id);
        if(cartao == null) {
            return ResponseEntity.notFound().build();
        }

        AvisoViagem avisoViagem = avisoViagemRequest.toModel(cartao, request);
        entityManager.persist(avisoViagem);

        return ResponseEntity.ok().build();
    }
}
