package br.com.orangetalents.proposta.avisarviagem.view;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import java.time.LocalDate;

@JsonComponent
public class ViagemCartaoRequest {

    private final Logger logger = LoggerFactory.getLogger(ViagemCartaoRequest.class);

    private String destino;
    private LocalDate validoAte;

    @JsonCreator
    public ViagemCartaoRequest(String destino, LocalDate validoAte) {
        logger.info("Enviando informações para sistema externo");
        this.destino = destino;
        this.validoAte = validoAte;
        Assertions.assertNotNull(destino, "Bug ao enviar informações para sistema externo");
        Assertions.assertNotNull(validoAte, "Bug ao enviar informações para sistema externo");
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
