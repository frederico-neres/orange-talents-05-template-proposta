package br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
public class AvisoViagem {

    @Id
    private UUID id;
    @NotBlank
    private String destino;
    @ManyToOne
    private Cartao cartao;
    @NotNull
    @Future
    private OffsetDateTime dataTermino;
    private OffsetDateTime criadoEm;
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;


    public AvisoViagem(Cartao cartao, @NotBlank String destino, @NotNull @Future OffsetDateTime dataTermino, @NotBlank String ipCliente, @NotBlank String userAgent) {
        this.id = UUID.randomUUID();
        this.cartao = cartao;
        this.criadoEm = OffsetDateTime.now();
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }
}
