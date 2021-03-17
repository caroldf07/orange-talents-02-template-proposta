package br.com.orangetalents.proposta.vincularcartaoaproposta.view;

import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class CartaoResponse {

    private final Logger logger = LoggerFactory.getLogger(CartaoResponse.class);

    @NotBlank
    private String id;
    @NotBlank
    private String emitidoEm;
    @NotBlank
    private String titular;
    private Set<BloqueioResponse> bloqueios;
    private Set<CarteiraDigitalResponse> carteiras;
    private Set<ParcelaResponse> parcelas;
    private int limite;
    @NotNull
    @JsonDeserialize(as = VencimentoResponse.class)
    private VencimentoResponse vencimento;

    @JsonCreator
    public CartaoResponse(@NotBlank String id,
                          @NotBlank String emitidoEm,
                          @NotBlank String titular,
                          Set<BloqueioResponse> bloqueios,
                          Set<CarteiraDigitalResponse> carteiras,
                          Set<ParcelaResponse> parcelas,
                          int limite,
                          @NotNull VencimentoResponse vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.vencimento = vencimento;
        Assertions.assertNotNull(id, "Bug no retorno do sistema de cartão");
    }

    public Cartao toModel() {
        logger.info("Convertendo infos do cartão");

        return new Cartao(this.id, LocalDateTime.parse(this.emitidoEm, ISO_LOCAL_DATE_TIME), this.titular,
                this.bloqueios, this.carteiras, this.parcelas,
                new BigDecimal(this.limite), this.vencimento);
    }

}
