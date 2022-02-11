package br.com.orangetalents.proposta.analisarclientecartao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class AnaliseClienteRequest {

  private final Logger logger = LoggerFactory.getLogger(AnaliseClienteRequest.class);

  @NotBlank private String documento;

  @NotBlank private String nome;

  @NotBlank private String idProposta;

  public AnaliseClienteRequest(
      @NotBlank String documento, @NotBlank String nome, @NotBlank String idProposta) {
    logger.info("Enviando proposta para análise");

    this.documento = documento;
    this.nome = nome;
    this.idProposta = idProposta;
    assertNotNull(documento, "Bug com o envio do documento");
    assertNotNull(nome, "Bug com o envio do nome do cliente");
    assertNotNull(idProposta, "Bug com o envio do id da Proposta");
  }

  /*
   * Criado por conta do jackson
   * */
  @Deprecated
  public AnaliseClienteRequest() {}

  public String getDocumento() {
    return documento;
  }

  public String getNome() {
    return nome;
  }

  public String getIdProposta() {
    return idProposta;
  }
}
