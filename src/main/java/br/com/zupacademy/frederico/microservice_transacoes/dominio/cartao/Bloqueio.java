package br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
public class Bloqueio {

    @Id
    private UUID id;
    private OffsetDateTime instanteBloqueio;
    private String ipClient;
    private String userAgent;
    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(Cartao cartao, String ipClient, String userAgent) {
        this.id = UUID.randomUUID();
        this.instanteBloqueio = OffsetDateTime.now();
        this.cartao = cartao;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
