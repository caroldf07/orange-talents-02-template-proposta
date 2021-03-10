package br.com.orangetalents.proposta.criacaoproposta.Model;

import br.com.orangetalents.proposta.analisecartao.AnaliseClienteRequest;
import br.com.orangetalents.proposta.criacaoproposta.Validacao.CpfCnpj;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Transient
    private final Logger logger = LoggerFactory.getLogger(Proposta.class);

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

        logger.info("Verificando informações recebidas da proposta");
        Assertions.assertNotNull(endereco, "Endereço veio inválido");

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

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public AnaliseClienteRequest paraAnaliseCartao() {
        return new AnaliseClienteRequest(getDocumento(), getNome(), getId().toString());
    }
}
