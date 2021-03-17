package br.com.orangetalents.proposta.bloquearcartao.view;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class BloqueioCartaoRequest {

    private final Logger logger = LoggerFactory.getLogger(BloqueioCartaoRequest.class);
    private String sistemaResponsavel = "api-carol";

    public BloqueioCartaoRequest(String sistemaResponsavel) {
        logger.info("Enviando sistema de solicitante: " + sistemaResponsavel);

        this.sistemaResponsavel = sistemaResponsavel;
        Assertions.assertNotNull(sistemaResponsavel, "Bug ao enviar requisição para sistema externo");
    }

    /*
     * Criado por conta do Jackson
     * */
    @Deprecated
    public BloqueioCartaoRequest() {
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
