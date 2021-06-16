package br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

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
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salarioBruto;
    @Enumerated(EnumType.STRING)
    private StatusProposta status;
    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Transient
    final static private String key = "ca97d7362618da06b6879ff0e63f3a6a3227a3fabe2b34918ac7e054de9050c7d3352ce735a62074";
    @Transient
    final static private String salt = "ced6c100b5a877ac";

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
                    @NotBlank String endereco, @NotNull @Positive BigDecimal salarioBruto) {

        this.criptografarDocumento(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salarioBruto = salarioBruto;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroCartao() {
        return cartao.getId();
    }

    public void mudarStatus(StatusProposta status) {
        this.status = status;
    }

    public void atrelarCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public StatusProposta getStatus() {
        return status;
    }

    private void criptografarDocumento(String documento) {
        this.documento = Proposta.criptografarString(documento);
    }

    public static String criptografarString(String text) {
        TextEncryptor textEncryptor = Encryptors.queryableText(key, salt);
        String encrypt = textEncryptor.encrypt(text);

        return encrypt;
    }

}

