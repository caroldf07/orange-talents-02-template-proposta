package br.com.orangetalents.proposta.criarcarteira.view;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NovaCarteiraResponse {

    private final Logger logger = LoggerFactory.getLogger(NovaCarteiraResponse.class);

    private String id;
    private String resultado;

    @JsonCreator
    public NovaCarteiraResponse(String id, String resultado) {
        logger.info("Recebendo resultado do sistema externo: " + resultado);

        this.id = id;
        this.resultado = resultado;

        Assertions.assertNotNull(id, "Bug ao receber informações do sistema externo");
        Assertions.assertNotNull(resultado, "Bug ao receber informações do sistema externo");
    }

    public String getId() {
        return id;
    }
}
