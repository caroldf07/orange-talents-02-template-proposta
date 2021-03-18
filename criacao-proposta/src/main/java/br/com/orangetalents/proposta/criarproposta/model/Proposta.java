package br.com.orangetalents.proposta.criarproposta.model;

import br.com.orangetalents.proposta.analisarclientecartao.AnaliseClienteRequest;
import br.com.orangetalents.proposta.analisarclientecartao.view.AnaliseClienteResponse;
import br.com.orangetalents.proposta.criarproposta.view.PropostaConsultaResponse;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Entity
public class Proposta {

    @Transient
    private final Logger logger = LoggerFactory.getLogger(Proposta.class);

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    @Email
    private String email;

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

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @OneToOne
    private Cartao cartao;


    public Proposta(@NotBlank @Email String email,
                    @NotBlank String documento,
                    @NotBlank String nome,
                    @NotNull Endereco endereco,
                    @NotNull @Positive BigDecimal salario) {
        this.email = email;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        this.documento = encoder.encode(documento);
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;

        logger.info("Verificando informações recebidas da proposta");
        assertNotNull(endereco, "Endereço veio inválido");

    }

    /*
     * Criado para o hibernate
     * */
    @Deprecated
    public Proposta() {
    }

    public String getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public AnaliseClienteRequest fromModelToRequest() {
        logger.info("Preparando cliente para envio");
        return new AnaliseClienteRequest(this.getDocumento(),
                this.getNome(), this.getId());
    }

    /*
     * Retorno do resultado da análise
     * */
    public void resultadoAnalise(@Valid AnaliseClienteResponse analiseClienteResponse) {
        this.statusProposta = analiseClienteResponse.getStatusProposta();
        assertNotNull(analiseClienteResponse, "Bug no retorno da análise do cliente");
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public PropostaConsultaResponse fromModelToPropostaConsulta() {
        return new PropostaConsultaResponse(this.statusProposta);
    }

    public void cartaoCriado(@Valid Cartao cartao) {
        this.cartao = cartao;
    }
}
