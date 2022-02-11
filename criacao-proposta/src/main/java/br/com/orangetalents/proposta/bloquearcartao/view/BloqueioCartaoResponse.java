package br.com.orangetalents.proposta.bloquearcartao.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloqueioCartaoResponse {

  private final Logger logger = LoggerFactory.getLogger(BloqueioCartaoResponse.class);

  @NotBlank private String resultado;

  @JsonCreator
  public BloqueioCartaoResponse(@NotBlank String resultado) {
    logger.info("Obtendo retorno do sistema externo: " + resultado);
    this.resultado = resultado;
    assertNotNull(resultado, "Bug no retorno do sistema de cartão");
  }

  /*
   * Criado por conta do Jackson
   * */
  @Deprecated
  public BloqueioCartaoResponse() {}

  public String getResultado() {
    return resultado;
  }
}
