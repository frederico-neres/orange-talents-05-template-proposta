package br.com.zupacademy.frederico.microservice_transacoes.api;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.dto.PropostaAcompanhamentoResponse;
import br.com.zupacademy.frederico.microservice_transacoes.metricas.Metricas;
import br.com.zupacademy.frederico.microservice_transacoes.repository.PropostaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/propostas/acompanhamento")
public class PropostaAcompanhamento {

    PropostaRepository propostaRepository;
    Metricas metricas;

    public PropostaAcompanhamento(PropostaRepository propostaRepository, Metricas metricas) {
        this.propostaRepository = propostaRepository;
        this.metricas = metricas;
    }

    @GetMapping("/{idProposta}")
    ResponseEntity<?> acompanhamentoById(@PathVariable UUID idProposta) {
        Optional<Proposta> proposta = propostaRepository.findById(idProposta);

        if(!proposta.isPresent()) { return ResponseEntity.notFound().build();}

        metricas.timer();
        return ResponseEntity.ok().body(new PropostaAcompanhamentoResponse(proposta.get()));
    }
}
