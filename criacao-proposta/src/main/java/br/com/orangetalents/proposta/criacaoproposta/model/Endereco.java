package br.com.orangetalents.proposta.criacaoproposta.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

    @Length(max = 2)
    @NotBlank
    private String UF;

    @NotBlank
    private String cidade;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String complemento;

    public Endereco(@Length(max = 2) @NotBlank String UF,
                    @NotBlank String cidade,
                    @NotBlank String logradouro,
                    @NotBlank String complemento) {
        this.UF = UF.toUpperCase();
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.complemento = complemento;
    }

    /*
     * Criado para o hibernate
     * */
    @Deprecated
    public Endereco() {
    }

}
