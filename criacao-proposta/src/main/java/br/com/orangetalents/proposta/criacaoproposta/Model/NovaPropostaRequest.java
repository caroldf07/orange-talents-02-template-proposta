package br.com.orangetalents.proposta.criacaoproposta.Model;

import br.com.orangetalents.proposta.criacaoproposta.Validacao.CpfCnpj;

import javax.persistence.Embedded;
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
    @Embedded
    private Endereco endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public NovaPropostaRequest(@NotNull String documento,
                               @NotBlank @Email String email,
                               @NotBlank String nome,
                               @NotNull @Valid Endereco endereco,
                               @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "NovaPropostaRequest{" +
                "documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", salario=" + salario +
                '}';
    }

    public Proposta toModel() {
        return new Proposta(this.email, this.nome, this.endereco, this.salario);
    }
}
