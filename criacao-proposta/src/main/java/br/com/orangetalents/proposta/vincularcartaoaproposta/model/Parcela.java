package br.com.orangetalents.proposta.vincularcartaoaproposta.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Parcela {
  @Id private String id;
  private Integer quantidade;
  private BigDecimal valor;
  @ManyToOne private Cartao cartao;

  public Parcela(String id, Integer quantidade, BigDecimal valor) {
    this.id = id;
    this.quantidade = quantidade;
    this.valor = valor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Parcela parcela = (Parcela) o;
    return Objects.equals(id, parcela.id) && Objects.equals(valor, parcela.valor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, valor);
  }
}
