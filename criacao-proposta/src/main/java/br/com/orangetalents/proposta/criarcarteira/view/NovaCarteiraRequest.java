package br.com.orangetalents.proposta.criarcarteira.view;

import br.com.orangetalents.proposta.criarcarteira.model.Carteira;
import br.com.orangetalents.proposta.vincularcartaoaproposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCarteiraRequest {

  @NotBlank @Email private String email;

  @NotNull
  @Enumerated(EnumType.STRING)
  private CarteiraEnum carteira;

  @JsonCreator
  public NovaCarteiraRequest(@NotBlank @Email String email, CarteiraEnum carteira) {
    this.email = email;
    this.carteira = carteira;
  }

  public String getEmail() {
    return email;
  }

  public CarteiraEnum getCarteira() {
    return carteira;
  }

  public Carteira toModel(NovaCarteiraResponse novaCarteiraResponse, @Valid Cartao cartao) {
    return new Carteira(novaCarteiraResponse.getId(), this.email, this.carteira, cartao);
  }
}
