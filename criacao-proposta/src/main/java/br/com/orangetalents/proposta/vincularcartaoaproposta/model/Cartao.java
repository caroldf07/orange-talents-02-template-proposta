package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import br.com.orangetalents.proposta.criacaoproposta.model.Proposta;
import br.com.orangetalents.proposta.criacaoproposta.repository.PropostaRepository;
import br.com.orangetalents.proposta.vincularcartaoaproposta.view.*;
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

    @OneToOne
    private Vencimento vencimento;

    @OneToOne
    @JoinColumn(name = "propostaId")
    private Proposta Proposta;

    public Cartao(String id, LocalDateTime emitidoEm,
                  String titular, Set<BloqueioResponse> bloqueios,
                  Set<AvisoViagemResponse> avisos, Set<CarteiraDigitalResponse> carteiras,
                  Set<ParcelaResponse> parcelas, BigDecimal limite,
                  VencimentoResponse vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios.addAll(bloqueios.stream().map(bloqueio -> bloqueio.toModel()).collect(Collectors.toSet()));
        this.avisos.addAll(avisos.stream().map(aviso -> aviso.toModel()).collect(Collectors.toSet()));
        this.carteiras.addAll(carteiras.stream().map(carteira -> carteira.toModel()).collect(Collectors.toSet()));
        this.parcelas.addAll(parcelas.stream().map(parcela -> parcela.toModel()).collect(Collectors.toSet()));
        this.limite = limite;
        this.vencimento = vencimento.toModel();
        Assertions.assertNotNull(id, "Bug na criação do cartão");
    }

    public String getId() {
        return id;
    }
}