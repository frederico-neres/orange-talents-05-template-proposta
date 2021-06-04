package br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Proposta {

    @Id
    @Column(unique = true)
    private UUID id;
    @NotBlank
    @Column(unique = true)
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereço;
    @NotNull
    @Positive
    private BigDecimal salarioBruto;
    @Enumerated(EnumType.STRING)
    private StatusProposta status;


    public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
                    @NotBlank String endereço, @NotNull @Positive BigDecimal salarioBruto) {

        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereço = endereço;
        this.salarioBruto = salarioBruto;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void mudarStatus(StatusProposta status) {
        this.status = status;
    }

    public StatusProposta getStatus() {
        return status;
    }
}

