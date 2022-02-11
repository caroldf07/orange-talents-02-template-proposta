package br.com.orangetalents.proposta.vincularcartaoaproposta.view;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Bloqueio;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloqueioResponse {

  private final Logger logger = LoggerFactory.getLogger(BloqueioResponse.class);

  private String id;
  private String bloqueadoEm;
  private String sistemaResponsavel;
  private boolean ativo;

  @JsonCreator
  public BloqueioResponse(String id, String bloqueadoEm, String sistemaResponsavel, boolean ativo) {
    this.id = id;
    this.bloqueadoEm = bloqueadoEm;
    this.sistemaResponsavel = sistemaResponsavel;
    this.ativo = ativo;
  }

  public Bloqueio toModel() {
    logger.info("Convertendo infos de bloqueio");
    return new Bloqueio(
        this.id,
        LocalDateTime.parse(this.bloqueadoEm, ISO_LOCAL_DATE_TIME),
        this.sistemaResponsavel,
        this.ativo);
  }

  public String getId() {
    return id;
  }

  public String getBloqueadoEm() {
    return bloqueadoEm;
  }

  public String getSistemaResponsavel() {
    return sistemaResponsavel;
  }

  public boolean isAtivo() {
    return ativo;
  }
}
