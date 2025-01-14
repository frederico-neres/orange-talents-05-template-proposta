package br.com.zupacademy.frederico.microservice_transacoes.repository;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

    List<Proposta> findByStatusAndCartao_Id(StatusProposta status, String cartao);
}
