package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class Vencimento {

  @Id private String id;
  private Integer dia;
  private LocalDateTime dataDeCriacao;

  public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
    this.id = id;
    this.dia = dia;
    this.dataDeCriacao = dataDeCriacao;
  }

  /*
   * Criado por conta do hibernate
   * */
  @Deprecated
  public Vencimento() {}
}
