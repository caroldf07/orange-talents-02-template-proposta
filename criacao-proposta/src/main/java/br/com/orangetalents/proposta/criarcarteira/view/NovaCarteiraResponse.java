package br.com.orangetalents.proposta.criarcarteira.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NovaCarteiraResponse {

  private final Logger logger = LoggerFactory.getLogger(NovaCarteiraResponse.class);

  @NotBlank private String id;

  @NotBlank private String resultado;

  @JsonCreator
  public NovaCarteiraResponse(@NotBlank String id, @NotBlank String resultado) {
    logger.info("Recebendo resultado do sistema externo: " + resultado);

    this.id = id;
    this.resultado = resultado;

    assertNotNull(id, "Bug ao receber informações do sistema externo");
    assertNotNull(resultado, "Bug ao receber informações do sistema externo");
  }

  public String getId() {
    return id;
  }
}
