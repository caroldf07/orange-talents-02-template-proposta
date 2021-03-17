package br.com.orangetalents.proposta.bloquearcartao.view;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import javax.validation.constraints.NotBlank;

@JsonComponent
public class BloqueioCartaoResponse {

    private final Logger logger = LoggerFactory.getLogger(BloqueioCartaoResponse.class);

    @NotBlank
    private String resultado;

    @JsonCreator
    public BloqueioCartaoResponse(@NotBlank String resultado) {
        logger.info("Obtendo retorno do sistema externo: " + resultado);
        this.resultado = resultado;
        Assertions.assertNotNull(resultado, "Bug no retorno do sistema de cartão");
    }

    /*
     * Criado por conta do Jackson
     * */
    @Deprecated
    public BloqueioCartaoResponse() {
    }

    public String getResultado() {
        return resultado;
    }
}
