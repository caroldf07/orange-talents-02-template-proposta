package br.com.orangetalents.proposta.criarcarteira.model;

import br.com.orangetalents.proposta.criarcarteira.view.CarteiraEnum;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class Carteira {

  @Id private String id;

  @Enumerated(EnumType.STRING)
  private CarteiraEnum carteira;

  private String email;
  @ManyToOne @Valid private Cartao cartao;

  public Carteira(String id, String email, CarteiraEnum carteira, @Valid Cartao cartao) {
    this.id = id;
    this.carteira = carteira;
    this.cartao = cartao;
    this.email = email;
  }

  /*
   * Criado por conta do hibernate
   * */
  @Deprecated
  public Carteira() {}

  public String getId() {
    return id;
  }

  public CarteiraEnum getCarteira() {
    return carteira;
  }

  public Cartao getCartao() {
    return cartao;
  }
}
