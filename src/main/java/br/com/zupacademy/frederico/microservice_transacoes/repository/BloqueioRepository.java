package br.com.zupacademy.frederico.microservice_transacoes.repository;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Bloqueio;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.EstadoCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BloqueioRepository extends JpaRepository<Bloqueio, UUID> {

    List<Bloqueio> findByCartao_EstadoCartao(EstadoCartao estadoCartao);
}
