package br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cartao {

    @Id
    private String id;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
