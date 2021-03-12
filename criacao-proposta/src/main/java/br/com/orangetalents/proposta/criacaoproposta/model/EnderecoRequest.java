package br.com.orangetalents.proposta.criacaoproposta.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {
    @Length(max = 2)
    @NotBlank
    private String UF;

    @NotBlank
    private String cidade;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String complemento;

    public EnderecoRequest(@Length(max = 2) @NotBlank String UF,
                           @NotBlank String cidade,
                           @NotBlank String logradouro,
                           @NotBlank String complemento) {
        this.UF = UF;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.complemento = complemento;
    }

    public Endereco toModel() {
        return new Endereco(this.UF, this.cidade, this.logradouro, this.complemento);
    }
}
