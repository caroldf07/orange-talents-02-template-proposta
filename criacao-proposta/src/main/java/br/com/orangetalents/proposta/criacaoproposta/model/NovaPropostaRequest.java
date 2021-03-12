package br.com.orangetalents.proposta.criacaoproposta.model;

import br.com.orangetalents.proposta.criacaoproposta.validacao.CpfCnpj;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotNull
    @CpfCnpj
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotNull
    @Valid
    private EnderecoRequest endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public NovaPropostaRequest(@NotNull String documento,
                               @NotBlank @Email String email,
                               @NotBlank String nome,
                               @NotNull @Valid EnderecoRequest endereco,
                               @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(this.email, this.documento, this.nome, this.endereco.toModel(), this.salario);
    }

    public String getDocumento() {
        return documento;
    }
}
