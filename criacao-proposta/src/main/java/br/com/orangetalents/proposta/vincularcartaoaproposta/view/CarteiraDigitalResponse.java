package br.com.orangetalents.proposta.vincularcartaoaproposta.view;

import br.com.orangetalents.proposta.criarproposta.controller.PropostaController;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.CarteiraDigital;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class CarteiraDigitalResponse {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    private String id;
    @Email
    private String email;
    private String associadaEm;
    private String emissor;

    @JsonCreator
    public CarteiraDigitalResponse(String id,
                                   @Email String email,
                                   String associadaEm,
                                   String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public CarteiraDigital toModel() {
        logger.info("Convertendo infos de carteira");
        return new CarteiraDigital(this.id, this.email, LocalDateTime.parse(this.associadaEm, ISO_LOCAL_DATE_TIME), this.emissor);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
