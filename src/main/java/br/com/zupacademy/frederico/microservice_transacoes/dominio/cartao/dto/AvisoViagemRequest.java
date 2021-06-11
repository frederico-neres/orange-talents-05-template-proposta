package br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.dto;

import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.AvisoViagem;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.cartao.Cartao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;
    @NotNull
    @Future
    private OffsetDateTime dataTermino;

    public AvisoViagemRequest(@NotBlank String destino, @NotNull @Future OffsetDateTime dataTermino) {
        this.destino = destino;
        this.dataTermino = dataTermino;
    }

    public AvisoViagem toModel(Cartao cartao, HttpServletRequest request) {
        String ipClient = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        return new AvisoViagem(cartao, destino, dataTermino, ipClient, userAgent);
    }
}
