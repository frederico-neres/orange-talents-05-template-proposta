package br.com.zupacademy.frederico.microservice_transacoes.dominio.biometria;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
public class Biometria {

    @Id
    private UUID id;
    @NotBlank
    @Lob
    private String fingerprint;
    @ManyToOne
    private Cartao cartao;
    private OffsetDateTime criadoEm;

    public Biometria(String fingerprint, Cartao cartao) {
        this.id = UUID.randomUUID();
        this.criadoEm = OffsetDateTime.now();
        this.fingerprint = fingerprint;
        this.cartao = cartao;
    }

    public UUID getId() {
        return id;
    }
}
