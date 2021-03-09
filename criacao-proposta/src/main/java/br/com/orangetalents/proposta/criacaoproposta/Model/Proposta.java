package br.com.orangetalents.proposta.criacaoproposta.Model;

import br.com.orangetalents.proposta.criacaoproposta.Validacao.CpfCnpj;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @CpfCnpj
    @Column(unique = true)
    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotNull
    @Embedded
    private Endereco endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public Proposta(@NotBlank @Email String email,
                    @NotBlank String documento,
                    @NotBlank String nome,
                    @NotNull Endereco endereco,
                    @NotNull @Positive BigDecimal salario) {
        this.email = email;
        this.documento = documento;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    /*
     * Criado para o hibernate
     * */
    @Deprecated
    public Proposta() {
    }

    public Long getId() {
        return id;
    }
}
