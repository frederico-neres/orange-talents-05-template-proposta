package br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
public class Cartao {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private EstadoCartao estadoCartao;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id) {
        this.id = id;
        this.estadoCartao = EstadoCartao.DESBLOQUEADO;
    }

    public String getId() {
        return id;
    }


    public Boolean isCartaoBloqueado() {
        if(this.estadoCartao.equals(null)) return false;
        return this.estadoCartao.equals(EstadoCartao.BLOQUEADO);
    }

    public Boolean isCartaoSolicitacaoBloqueio() {
        if(this.estadoCartao.equals(null)) return false;
        return this.estadoCartao.equals(EstadoCartao.SOLICITACAO_BLOQUEIO);
    }

    public Boolean isCartaoBloqueadoOrSolicitacaoBloqueio() {
        if(this.estadoCartao.equals(null)) return false;
        return this.isCartaoBloqueado() || this.isCartaoSolicitacaoBloqueio();
    }

    public void estadoCartaoParaBloqueado() {
        this.estadoCartao = EstadoCartao.BLOQUEADO;
    }

    public void estadoCartaoParaSolicitacaoBloqueio() {
        this.estadoCartao = EstadoCartao.SOLICITACAO_BLOQUEIO;
    }


}
