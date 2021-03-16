package br.com.orangetalents.proposta.vincularcartaoaproposta.view;

import br.com.orangetalents.proposta.criarproposta.controller.PropostaController;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.AvisoViagem;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class AvisoViagemResponse {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    private String validoAte;
    private String destino;

    @JsonCreator
    public AvisoViagemResponse(String validoAte,
                               String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagem toModel() {
        logger.info("Convertendo infos de avisos");
        return new AvisoViagem(LocalDateTime.parse(validoAte, ISO_LOCAL_DATE_TIME), this.destino);
    }

    public String getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
