package br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cartao {

    @Id
    private String id;
    @OneToOne(mappedBy = "cartao")
    private Bloqueio bloqueio;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Bloqueio getBloqueio() {
        return bloqueio;
    }
}
