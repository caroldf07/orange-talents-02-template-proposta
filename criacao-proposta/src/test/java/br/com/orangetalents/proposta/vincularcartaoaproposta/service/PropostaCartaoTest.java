package br.com.orangetalents.proposta.vincularcartaoaproposta.service;

import br.com.orangetalents.proposta.criarproposta.model.Endereco;
import br.com.orangetalents.proposta.criarproposta.model.Proposta;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class PropostaCartaoTest {

  static Stream<Arguments> gerador1() {
    return Stream.of(
        Arguments.of(
            new Proposta(
                "email@email",
                "12345678901",
                "Teste",
                new Endereco("SP", "São Paulo", "Rua ali da esquina", "01"),
                BigDecimal.TEN),
            null));
  }

  @DisplayName("O cartão deve ser nulo")
  @ParameterizedTest
  @MethodSource("gerador1")
  void vinculaCartaoProposta1(Proposta proposta, String cartao) {

    Assertions.assertEquals(proposta.getCartao(), cartao);
  }
}
