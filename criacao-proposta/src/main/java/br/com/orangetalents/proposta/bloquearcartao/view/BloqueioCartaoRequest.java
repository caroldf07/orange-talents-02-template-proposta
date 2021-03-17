package br.com.orangetalents.proposta.bloquearcartao.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import javax.validation.constraints.NotBlank;

@JsonComponent
public class BloqueioCartaoRequest {

    private final Logger logger = LoggerFactory.getLogger(BloqueioCartaoRequest.class);

    @NotBlank
    private String sistemaResponsavel;

    public BloqueioCartaoRequest(@NotBlank String sistemaResponsavel) {
        logger.info("Enviando sistema de solicitante: " + sistemaResponsavel);

        this.sistemaResponsavel = sistemaResponsavel;
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
