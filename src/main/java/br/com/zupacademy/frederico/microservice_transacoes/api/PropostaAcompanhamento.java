package br.com.zupacademy.frederico.microservice_transacoes.api;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.dto.PropostaAcompanhamentoResponse;
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

    public PropostaAcompanhamento(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @GetMapping("/{idProposta}")
    ResponseEntity<?> acompanhamentoById(@PathVariable UUID idProposta) {
        Optional<Proposta> proposta = propostaRepository.findById(idProposta);

        if(!proposta.isPresent()) { return ResponseEntity.notFound().build();}
        
        return ResponseEntity.ok().body(new PropostaAcompanhamentoResponse(proposta.get()));
    }
}
