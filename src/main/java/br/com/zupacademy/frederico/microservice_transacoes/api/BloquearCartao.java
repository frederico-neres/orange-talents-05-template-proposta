package br.com.zupacademy.frederico.microservice_transacoes.api;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Bloqueio;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/cartao/bloquear")
public class BloquearCartao {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity<?> bloquear(@PathVariable String idCartao, HttpServletRequest request) {

        Cartao cartao = entityManager.find(Cartao.class, idCartao);

        if(cartao == null) {
            return ResponseEntity.notFound().build();
        }

        Bloqueio bloqueioExistente = cartao.getBloqueio();
        if(bloqueioExistente != null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        String ipClient = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        Bloqueio bloqueio = new Bloqueio(cartao, ipClient, userAgent);
        entityManager.persist(bloqueio);

        return ResponseEntity.ok().build();
    }

}
