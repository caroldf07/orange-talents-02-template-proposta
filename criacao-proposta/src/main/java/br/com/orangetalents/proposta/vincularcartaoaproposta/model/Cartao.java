package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import br.com.orangetalents.proposta.bloquearcartao.controller.BloqueioSistemaExterno;
import br.com.orangetalents.proposta.bloquearcartao.view.BloqueioCartaoResponse;
import br.com.orangetalents.proposta.criarbiometria.model.Biometria;
import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import br.com.orangetalents.proposta.criarproposta.repository.PropostaRepository;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.BloqueioResponse;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.CarteiraDigitalResponse;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.ParcelaResponse;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.VencimentoResponse;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cartao {

    @Transient
    @Autowired
    private PropostaRepository propostaRepository;

    @Id
    @NotBlank
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;

    @OneToMany(mappedBy = "cartao")
    private Set<Bloqueio> bloqueios = new HashSet<>();

    @OneToMany(mappedBy = "cartao")
    private Set<AvisoViagem> avisos = new HashSet<>();

    @OneToMany(mappedBy = "cartao")
    private Set<CarteiraDigital> carteiras = new HashSet<>();

    @OneToMany(mappedBy = "cartao")
    private Set<Parcela> parcelas = new HashSet<>();

    private BigDecimal limite;

    @Embedded
    private Vencimento vencimento;

    @OneToOne
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao")
    private Set<Biometria> biometrias = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;

    @Autowired
    @Transient
    private BloqueioSistemaExterno bloqueioSistemaExterno;

    public Cartao(String id, LocalDateTime emitidoEm,
                  String titular, Set<BloqueioResponse> bloqueios, Set<CarteiraDigitalResponse> carteiras,
                  Set<ParcelaResponse> parcelas, BigDecimal limite,
                  VencimentoResponse vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios.addAll(bloqueios.stream().map(bloqueio -> bloqueio.toModel()).collect(Collectors.toSet()));
        this.carteiras.addAll(carteiras.stream().map(carteira -> carteira.toModel()).collect(Collectors.toSet()));
        this.parcelas.addAll(parcelas.stream().map(parcela -> parcela.toModel()).collect(Collectors.toSet()));
        this.limite = limite;
        this.vencimento = vencimento.toModel();
        this.statusCartao = StatusCartao.ATIVO;
        Assertions.assertNotNull(id, "Bug na criação do cartão");
    }

    /*
     * Criado por conta do hibernate
     * */
    @Deprecated
    public Cartao() {
    }

    public String getId() {
        return id;
    }

    public void propostaCriada(Proposta proposta) {
        this.proposta = proposta;
    }

    public Set<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public StatusCartao getStatusCartao() {
        return statusCartao;
    }

    public void alteraStatusCartao(BloqueioCartaoResponse bloqueioCartaoResponse) {
        Assertions.assertNotNull(bloqueioCartaoResponse, "Bug na integração");
        if (bloqueioCartaoResponse.equals(StatusCartao.BLOQUEADO)) {
            Assertions.assertEquals(bloqueioCartaoResponse, StatusCartao.BLOQUEADO);
            this.statusCartao = StatusCartao.BLOQUEADO;
        }

    }
}