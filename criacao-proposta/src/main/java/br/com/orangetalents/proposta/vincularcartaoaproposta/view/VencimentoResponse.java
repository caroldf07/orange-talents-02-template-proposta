package br.com.orangetalents.proposta.vincularcartaoaproposta.view;

import br.com.orangetalents.proposta.criacaoproposta.controller.PropostaController;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Vencimento;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class VencimentoResponse {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    private String id;
    private Integer dia;
    private String dataDeCriacao;


    @JsonCreator
    public VencimentoResponse(String id,
                              Integer dia,
                              String dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento toModel() {
        logger.info("Convertendo infos de vencimento");
        return new Vencimento(this.id, this.dia, LocalDateTime.parse(this.dataDeCriacao, ISO_LOCAL_DATE_TIME));
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }
}
