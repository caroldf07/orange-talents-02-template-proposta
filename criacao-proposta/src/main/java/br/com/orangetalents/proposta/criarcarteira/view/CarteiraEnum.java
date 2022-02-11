package br.com.orangetalents.proposta.criarcarteira.view;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CarteiraEnum {
  @JsonValue
  PAYPAL,
  @JsonValue
  SAMSUNGPAY;
}
