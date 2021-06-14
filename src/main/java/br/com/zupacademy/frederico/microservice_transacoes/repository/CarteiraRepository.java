package br.com.zupacademy.frederico.microservice_transacoes.repository;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.CarteiraDigital;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira.TipoCarteiraDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarteiraRepository extends JpaRepository<CarteiraDigital, UUID> {
    Optional<CarteiraDigital> findByCartaoAndCarteira(Cartao cartao, TipoCarteiraDigital carteiraDigital);
}
