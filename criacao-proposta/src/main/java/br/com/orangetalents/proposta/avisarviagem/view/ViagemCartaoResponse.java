package br.com.orangetalents.proposta.avisarviagem.view;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;

public class ViagemCartaoResponse {

    private final Logger logger = LoggerFactory.getLogger(ViagemCartaoResponse.class);

    @NotBlank
    private String resultado;

    @JsonCreator
    public ViagemCartaoResponse(@NotBlank String resultado) {
        logger.info("Obtendo retorno do sistema externo: " + resultado);
        this.resultado = resultado;
        Assertions.assertNotNull(resultado, "Bug no retorno do sistema de cartão");
    }

    /*
     * Criado por conta do Jackson
     * */
    @Deprecated
    public ViagemCartaoResponse() {
    }

    public String getResultado() {
        return resultado;
    }
}
