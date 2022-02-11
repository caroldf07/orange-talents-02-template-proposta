package br.com.orangetalents.proposta.vincularcartaoaproposta.view;

import br.com.orangetalents.proposta.criarproposta.controller.PropostaController;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Parcela;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParcelaResponse {

  private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

  private String id;
  private Integer quantidade;
  private Number valor;

  @JsonCreator
  public ParcelaResponse(String id, Integer quantidade, Number valor) {
    this.id = id;
    this.quantidade = quantidade;
    this.valor = valor;
  }

  public Parcela toModel() {
    logger.info("Convertendo infos de parcela");
    return new Parcela(this.id, this.quantidade, new BigDecimal((Double) this.valor));
  }

  public String getId() {
    return id;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public Number getValor() {
    return valor;
  }
}
