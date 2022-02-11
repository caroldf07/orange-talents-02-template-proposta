package br.com.orangetalents.proposta.analisarclientecartao.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.orangetalents.proposta.analisarclientecartao.ResultadoSolicitacao;
import br.com.orangetalents.proposta.criarproposta.model.StatusProposta;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnaliseClienteResponse {

  private final Logger logger = LoggerFactory.getLogger(AnaliseClienteResponse.class);

  @NotBlank private String documento;
  @NotBlank private String nome;
  @NotBlank private String idProposta;
  @NotNull private ResultadoSolicitacao resultadoSolicitacao;

  private StatusProposta statusProposta;

  /*
   * Deserializando o response body do sistema externo
   * */
  @JsonCreator
  public AnaliseClienteResponse(
      @JsonProperty("documento") @NotBlank String documento,
      @JsonProperty("nome") @NotBlank String nome,
      @JsonProperty("idProposta") @NotBlank String idProposta,
      @JsonProperty("resultadoSolicitacao") @NotNull ResultadoSolicitacao resultadoSolicitacao) {
    logger.info("Recebendo o retorno da análise da proposta");

    this.documento = documento;
    this.nome = nome;
    this.idProposta = idProposta;
    this.resultadoSolicitacao = resultadoSolicitacao;
    assertNotNull(resultadoSolicitacao, "Houve no retorno da requisição da análise");
  }

  public AnaliseClienteResponse(StatusProposta statusProposta) {
    this.statusProposta = statusProposta;
  }

  /*
   * Conversão do sistema externo para o nosso
   * */
  public StatusProposta getStatusProposta() {
    if (resultadoSolicitacao == ResultadoSolicitacao.SEM_RESTRICAO) {
      assertTrue(
          resultadoSolicitacao == ResultadoSolicitacao.SEM_RESTRICAO,
          "Bug na conversão do retorno da análise com a devolutiva");

      statusProposta = StatusProposta.ELEGIVEL;

      return statusProposta;
    }
    statusProposta = StatusProposta.NAO_ELEGIVEL;
    return statusProposta;
  }
}
