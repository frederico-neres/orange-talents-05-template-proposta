package br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.dto;

import br.com.zupacademy.frederico.microservice_transacoes.annotations.CPForCNPJ;
import br.com.zupacademy.frederico.microservice_transacoes.annotations.UniqueValueDocumento;
import br.com.zupacademy.frederico.microservice_transacoes.dominio.proposta.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CadastroPropostaResquest {

    @CPForCNPJ
    @NotBlank
    @UniqueValueDocumento()
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


    public CadastroPropostaResquest(@NotBlank String documento,@NotBlank @Email String email, @NotBlank String nome,
                                    @NotBlank String endereco, @NotNull @Positive BigDecimal salarioBruto) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salarioBruto = salarioBruto;
    }


    public Proposta toModel() {
        return new Proposta(documento, email, nome, endereco, salarioBruto);
    }

}
