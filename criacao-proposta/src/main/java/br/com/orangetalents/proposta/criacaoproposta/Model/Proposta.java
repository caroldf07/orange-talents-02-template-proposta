package br.com.orangetalents.proposta.criacaoproposta.Model;

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

    @NotBlank
    private String nome;

    @NotNull
    @Embedded
    private Endereco endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public Proposta(@NotBlank @Email String email, @NotBlank String nome, @NotNull Endereco endereco, @NotNull @Positive BigDecimal salario) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", salario=" + salario +
                '}';
    }


    public Long getId() {
        return id;
    }
}
