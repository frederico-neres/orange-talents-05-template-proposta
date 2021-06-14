package br.com.zupacademy.frederico.microservice_transacoes.dominio.carteira;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class CarteiraDigital {

    @Id
    private UUID id;
    @ManyToOne
    private Cartao cartao;
    @Email
    @NotBlank
    private String email;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoCarteiraDigital carteira;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(Cartao cartao, @Email @NotBlank String email, @NotBlank TipoCarteiraDigital carteira) {
        this.id = UUID.randomUUID();
        this.cartao = cartao;
        this.email = email;
        this.carteira = carteira;
    }
}
